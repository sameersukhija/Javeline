package com.lumata.e4o.testplan.functional.e2e;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.e4o.dao.tenant.DAOConf;
import com.lumata.e4o.schema.tenant.Conf;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;
import com.lumata.e4o.testing.common.ParentTestCase;
import com.lumata.e4o.testing.common.TCEKC;
import com.lumata.e4o.testing.common.TCEKCL;
import com.lumata.e4o.testing.common.TCMysqlMaster;

@TCMysqlMaster
@TCEKCL(
	@TCEKC( ssh_server="actrule1", ssh_user="root" )
)
public class TestAggregateData extends ParentTestCase {
	
	ArrayList<ArrayList<Conf>> confHistory = new ArrayList<ArrayList<Conf>>(); 
	
	@Test( enabled = true, priority = 1 )
	public void getTenatConfTableBeforeExpiredDataTask() {
		
		ArrayList<Conf> conf = DAOConf.getInstance( mysqlMaster ).getAll();
		
		confHistory.add( conf );
				
	}
	
	@Test( enabled = true, priority = 2 )
	public void execExpiredDataTask() {
		
		ExpressionKernelCommands.TaskStatus taskStatus = ekcl.get("actrule1", "root" ).execTask( 23500, 1, ExpressionKernelCommands.Task.ExpiredData );
		
		Assert.assertTrue( taskStatus.equals( ExpressionKernelCommands.TaskStatus.OK ) || taskStatus.equals( ExpressionKernelCommands.TaskStatus.ALREADY_DONE ) );
				
	}
		
	@Test( enabled = true, priority = 3 )
	public void getTenatConfTableBeforeAggregateDataTask() {
		
		ArrayList<Conf> conf = DAOConf.getInstance( mysqlMaster ).getAll();
		
		confHistory.add( conf );
		
		ArrayList<Conf> confDiff = compareConf( confHistory.get( confHistory.size() - 2 ), confHistory.get( confHistory.size() - 1 ) );
		
		System.out.println( "### BEFORE AGGREGATEDATA ###" );
		
		for( Conf confRow:  confDiff ) {
			
			System.out.println( confRow.getName() + ": " + confRow.getCurrent() );
			
		}
				
	}

	@Parameters({"environment", "tenant"})
	@Test( enabled = true, priority = 4 )
	public void execAggregateDataTask() {
		// delete from conf where section = "user_datas" and name like "last%"; --> to reset aggregateddate
		ExpressionKernelCommands.TaskStatus taskStatus = ekcl.get("actrule1", "root" ).execTask( 23500, 1, ExpressionKernelCommands.Task.AggregateData );
		
		Assert.assertTrue( taskStatus.equals( ExpressionKernelCommands.TaskStatus.OK ) || taskStatus.equals( ExpressionKernelCommands.TaskStatus.ALREADY_DONE ) );
		
	}
	
	@Test( enabled = true, priority = 5 )
	public void getTenatConfTableAfterAggregateDataTask() {
		
		ArrayList<Conf> conf = DAOConf.getInstance( mysqlMaster ).getAll();
		
		confHistory.add( conf );

		ArrayList<Conf> confDiff = compareConf( confHistory.get( confHistory.size() - 2 ), confHistory.get( confHistory.size() - 1 ) );
		
		System.out.println( "### AFTER AGGREGATEDATA ###" );
		
		for( Conf confRow:  confDiff ) {
			
			System.out.println( confRow.getName() + ": " + confRow.getCurrent() );
			
		}
		
	}
	
	private ArrayList<Conf> compareConf( ArrayList<Conf> confBefore, ArrayList<Conf> confAfter ) {
		
		ArrayList<Conf> confDiff = new ArrayList<Conf>();
		
		for( Conf confRowRight : confAfter ) {
				
			boolean found = false;
			
			for( Conf confRowLeft : confBefore ) {
						
				if( 
					confRowLeft.getName().equals( confRowRight.getName() ) &&	
					confRowLeft.getPosition().equals( confRowRight.getPosition() ) &&
					confRowLeft.getSection().equals( confRowRight.getSection() ) &&
					confRowLeft.getProcessId().equals( confRowRight.getProcessId() )
				) {
					
					found = true;
					
					boolean diff = false;
					
					if( null == confRowLeft.getCurrent() || null == confRowRight.getCurrent() ) {
						
						diff = confRowLeft.getCurrent() != confRowRight.getCurrent();
						
					} else {
						
						diff = !confRowLeft.getCurrent().equals( confRowRight.getCurrent() );
						
					}
					
					if( diff ) {
						
						String currDiff = "left: " + confRowLeft.getCurrent() + " - right: " + confRowRight.getCurrent();
						
						confRowLeft.setCurrent( currDiff );
						
						confDiff.add( confRowLeft );
						
					}
										
					break;
					
				}			
			
			}
			
			if( !found ) {
				
				confDiff.add( confRowRight );
				
			}
			
		}
		
		return confDiff;
		
	}

}