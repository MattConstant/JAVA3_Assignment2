package ca.sheridancollege.consmatt.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Lombok annotations to skip getters and setters
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {
	
	private static final long serialVersionUID = 8093097162994770302L;       //Creating Serializable, no fucking clue what this truly does
	
	private int id;
	private String name;
	private String employeeId;
	private double sunday;
	private double monday;
	private double tuesday;                  //Creating Constructors for bean
	private double wednesday;
	private double thursday;
	private double friday;
	private double saturday;
	private double wage;
	private double pay;
	private double totalHours;

	
	
	
	
	
	
	
	
	
	public void setPay(double pay) {      //You can also create accessors and mutators yourself
		pay = ( this.sunday + this.monday + this.tuesday + this.wednesday + this.thursday + this.friday + this.saturday);
		if(pay > 40) {
			pay = (pay - 40);
			pay = pay * (wage * 1.5);
			double extra = 40 * wage; 
			pay = pay + extra;
			this.pay = pay; 
		}
		else {
			pay = pay * wage;
			this.pay = pay; 
		}
		
		}
	
	public void setTotalHours(double totalHours) {
		this.totalHours =  ( this.sunday + this.monday + this.tuesday + this.wednesday + this.thursday + this.friday + this.saturday);
	}
	

	
	
	

}
