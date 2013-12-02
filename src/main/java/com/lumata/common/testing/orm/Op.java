package com.lumata.common.testing.orm;

public class Op implements IOp {

	public enum Types {
		
		eq(" = "),
		lt(" < "),
		let(" <= "),
		gt(" > "),
		get(" >= "),
		in(" IN ");
		
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
	public ExprFF eq( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.eq, right_field );
		
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
	public ExprFF get( final Enum<?> right_field ) {
		
		ExprFF expr = new ExprFF( field, Op.Types.get, right_field );
				
		return expr;
	
	}
	
	@Override
	public ExprFV in( final Object... values ) {
				
		ExprFV expr = new ExprFV( field, Op.Types.in, values );
		
		return expr;
	
	}

}
