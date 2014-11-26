package com.lumata.common.testing.orm;

import com.lumata.common.testing.annotations.mysql.Table;
import com.lumata.common.testing.orm.Statement.MysqlStatement;

public class MysqlOption implements IMysqlOption {

	public enum MysqlDumpOption {
		
		User("-u "),
		Password("-p "),
		Host("-h "),
		Port("-P ");
		
		private String option;
		
		MysqlDumpOption( String option ) {
			this.option = option;
		}
		
		public String alias() {
			return this.option;
		}
		
	}
	
	Dump dump;
	
	MysqlOption( MysqlDumpOption dumpOption, Dump dump ) {
		
		this.dump = dump;
		
		this.dump.append( dumpOption.alias() );
	
	}
	
	MysqlOption( MysqlDumpOption dumpOption, Object value, Dump dump ) {
		
		this.dump = dump;
		
		this.dump.append( dumpOption.alias() ).append( String.valueOf( value ) );
	
	}
	
	@Override
	public IMysqlOption user( String user ) {
		return new MysqlOption( MysqlDumpOption.User, user, dump );
	}
	
	@Override
	public IMysqlOption password( String password ) {
		return new MysqlOption( MysqlDumpOption.Password, password, dump );
	}
	
	@Override
	public IMysqlOption password() {
		return new MysqlOption( MysqlDumpOption.Password, dump );
	}
	
	@Override
	public IMysqlOption host( String host ) {
		return new MysqlOption( MysqlDumpOption.Host, host, dump );
	}
	
	@Override
	public IMysqlOption port( String port ) {
		return new MysqlOption( MysqlDumpOption.Port, port, dump );
	}
//	@Override
//	public IFrom from( Object entity ) {
//		
//		Table table = (Table)entity.getClass().getAnnotation( Table.class );
//		
//		this.statement.addEntity( entity, table.value() );
//		
//		this.statement.append( MysqlStatement.FROM.getName() )
//						.append( table.value() );
//			
//		return new From(statement);
//	
//	}
//	
//	@Override
//	public IFrom from( Object entity, String suffix ) {
//		
//		Table table = (Table)entity.getClass().getAnnotation( Table.class );
//		
//		this.statement.addEntity( entity, table.value() + suffix );
//		
//		this.statement.append( MysqlStatement.FROM.getName() )
//						.append( table.value() );
//			
//		return new From(statement);
//	
//	}

}
