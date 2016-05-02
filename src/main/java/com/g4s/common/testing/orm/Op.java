package com.g4s.common.testing.orm;

public class Op implements IOp {

	public enum Types {
		
		eq(" = "),
		neq(" != "),
		lt(" < "),
		let(" <= "),
		gt(" > "),
		get(" >= "),
		is(" IS "),
		is_not(" IS NOT "),
		in(" IN "),
		not_in(" NOT IN "),
		like( " LIKE " );
		
		private String value;
		
		Types( final String value ) {
			this.value = value;
		}
		
		public String value() {
			return this.value;
		}
		
	}
	
	Enum<?> field;
	
	Op( final Enum<?> field ) {
		this.field = field;
	}
		
	@Override
	public ExprFV eq() {
		
		ExprFV expr = new ExprFV( field, Op.Types.eq );
				
		return expr;
	
	}

	@Override
	public ExprFV eq( final Object value ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.eq, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFV eq( final ISelect select ) {
		
		ExprFV expr = null;// = new ExprFV( field, Op.Types.eq, value );
				
		return expr;
	
	}

	@Override
	public ExprFF eq( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.eq, right_field );
		
		return expr;
	
	}

	@Override
	public ExprFV neq() {
		
		ExprFV expr = new ExprFV( field, Op.Types.neq );
				
		return expr;
	
	}

	@Override
	public ExprFV neq( final Object value ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.neq, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFV neq( final ISelect select ) {
		
		ExprFV expr = null;// = new ExprFV( field, Op.Types.eq, value );
				
		return expr;
	
	}

	@Override
	public ExprFF neq( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.neq, right_field );
		
		return expr;
	
	}
		
	@Override
	public ExprFV lt() {
		
		ExprFV expr = new ExprFV( field, Op.Types.lt );
				
		return expr;
	
	}
	
	@Override
	public ExprFV lt( final Object value ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.lt, value );
	
		return expr;
	
	}
	
	@Override
	public ExprFV lt( final ISelect select ) {
		
		ExprFV expr = null;// = new ExprFV( field, Op.Types.eq, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFF lt( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.lt, right_field );
			
		return expr;
	
	}

	@Override
	public ExprFV let() {
		
		ExprFV expr = new ExprFV( field, Op.Types.let );
				
		return expr;
	
	}
	
	@Override
	public ExprFV let( final Object value ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.let, value );
		
		return expr;
	
	}
	
	@Override
	public ExprFV let( final ISelect select ) {
		
		ExprFV expr = null;// = new ExprFV( field, Op.Types.eq, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFF let( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.let, right_field );
			
		return expr;
	
	}

	@Override
	public ExprFV gt() {
		
		ExprFV expr = new ExprFV( field, Op.Types.gt );
				
		return expr;
	
	}
	
	@Override
	public ExprFV gt( final Object value ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.gt, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFV gt( final ISelect select ) {
		
		ExprFV expr = null;// = new ExprFV( field, Op.Types.eq, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFF gt( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.gt, right_field );
				
		return expr;
	
	}

	@Override
	public ExprFV get() {
		
		ExprFV expr = new ExprFV( field, Op.Types.get );
				
		return expr;
	
	}
	
	@Override
	public ExprFV get( final Object value ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.get, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFV get( final ISelect select ) {
		
		ExprFV expr = null;// = new ExprFV( field, Op.Types.eq, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFF get( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.get, right_field );
				
		return expr;
	
	}
	
	@Override
	public ExprFV is() {
		
		ExprFV expr = new ExprFV( field, Op.Types.is );
				
		return expr;
	
	}

	@Override
	public ExprFV is( final Sub select ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.eq, select.getValue() );
				
		return expr;
	
	}
	
	@Override
	public ExprFV is( Object value ) {
				
		ExprFV expr = new ExprFV( field, Op.Types.is, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFF is( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.is, right_field );
		
		return expr;
	
	}
	
	@Override
	public ExprFV is_not() {
		
		ExprFV expr = new ExprFV( field, Op.Types.is_not );
				
		return expr;
	
	}

	@Override
	public ExprFV is_not( final Object value ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.is_not, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFV is_not( final ISelect select ) {
		
		ExprFV expr = null;// = new ExprFV( field, Op.Types.eq, value );
				
		return expr;
	
	}
	
	@Override
	public ExprFF is_not( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.is_not, right_field );
		
		return expr;
	
	}
	
	@Override
	public ExprFV in( final Statement statement ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.in, statement.build().replace( ";" , "" ) );
		
		return expr;
	
	}
	
	@Override
	public ExprFV in( final Object... values ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.in, values );
		
		return expr;
	
	}

	@Override
	public ExprFV not_in( final Statement statement ) {
				
		ExprFV expr = new ExprFV( field, Op.Types.not_in, statement.build().replace( ";" , "" ) );
		
		return expr;
	
	}
	
	@Override
	public ExprFV not_in( final Object... values ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.not_in, values );
		
		return expr;
	
	}
	
/*
	@Override
	public ExprFV like() {
		
		ExprFV expr = new ExprFV( field, Op.Types.like );
				
		return expr;
	
	}
*/
	@Override
	public ExprFV like( final String value ) {
		
		ExprFV expr = new ExprFV( field, Op.Types.like, "%" + value + "%" );
				
		return expr;
	
	}
/*	
	@Override
	public ExprFV like( final ISelect select ) {
		
		ExprFV expr = null;// = new ExprFV( field, Op.Types.eq, value );
				
		return expr;
	
	}

	@Override
	public ExprFF like( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.like, right_field );
		
		return expr;
	
	}
*/
}
