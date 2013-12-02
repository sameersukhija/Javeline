package com.lumata.common.testing.orm;

public class ExprFF implements IExprFF {
	
	private Enum<?> left_field;
	private Op.Types op;
	private Enum<?> right_field;
	
	public ExprFF() {}
	
	public ExprFF( Enum<?> left_field, Op.Types op, Enum<?> right_field ) {
		this.setLeftField( left_field );
		this.setOp( op );
		this.setRightField( right_field );
	}
	
	public Enum<?> getLeftField() {
		return this.left_field;
	}
	
	public Op.Types getOp() {
		return this.op;
	}
	
	public Enum<?> getRightField() {
		return this.right_field;
	}

	public void setLeftField( Enum<?> left_field ) {
		this.left_field = left_field;
	}
	
	public void setOp( Op.Types op ) {
		this.op = op;
	}
	
	public void setRightField( Enum<?> right_field ) {
		this.right_field = right_field;
	}
	
}
