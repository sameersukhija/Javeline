package com.lumata.expression.operators.testing.a;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.lumata.common.testing.annotations.mysql.Column;
import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.orm.EnumFields;
import com.lumata.common.testing.orm.IEnumFields;
import com.lumata.common.testing.orm.Statement;
import com.lumata.common.testing.orm.Statement.MysqlStatement;
//import com.lumata.expression.operators.testing.pojo.autogenerator.Agencies;

public class TestAgencies {

	public enum a { pippo }
	
	@Test
	public void testEnum() {
		
		//IEnumFields ef = new EnumFields(Agencies.Fields.id);
		//Column col = ef.col();
		//System.out.println(col);
		
		
		
		
		
		
		
		
		
		
		
		/*		
		Table tb = (Table)Agencies.Fields.name.getClass().getEnclosingClass().getAnnotation( Table.class );
		System.out.println( tb.value() );
		*/
		
		//Statement statement = new Statement( MysqlStatement.SELECT);
		//statement.fields = new ArrayList<IEnumFields>();
		//statement.fields.add( new EnumFields<>( Agencies.Fields.id ) );
		//statement.create();
		
		
	}
	
}
