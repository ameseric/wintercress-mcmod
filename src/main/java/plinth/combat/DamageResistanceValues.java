package plinth.combat;

import java.util.HashMap;

public class DamageResistanceValues {

	
	private HashMap<DamageType ,Integer> resistances;
	
	
	public DamageResistanceValues() {
		
		this.resistances = new HashMap<>();
		
		for( DamageType dt : DamageType.values()) {
			resistances.put( dt ,0);
		}		
	}
	
	
	public DamageResistanceValues( int[] values) {
		
		this.resistances = new HashMap<>();
		
		for( DamageType dt : DamageType.values()) {
			resistances.put( dt ,values[dt.ordinal()]);
		}
	}
	
	
	
	
	public int[] getIntegerArray() {
		int[] arr = new int[DamageType.values().length];
		
		for( DamageType dt : DamageType.values()) {
			arr[dt.ordinal()] = resistances.get(dt);
		}
		
		return arr;
	}
	
	
	public void addResistances( int[] values) {
		if( values.length < DamageType.values().length) {
			System.out.println( "ERROR! Entity resistance values don't match up with known DamageTypes!");
			return;
		}
		for( DamageType dt : DamageType.values()) {
			int oldResistance = resistances.get( dt);
			int addResistance = values[dt.ordinal()];
			resistances.put( dt ,oldResistance + addResistance); 
		}
	}
	
	
	
	public void setResistance( DamageType dt ,int resistValue) {
		this.resistances.put( dt ,resistValue);
	}
	
	
	public float getDamageAmount( DamageType dt ,float damageAmount) {
//		System.out.println( "Calculating damage, type " + dt.name() + " and amount " + damageAmount);
//		System.out.println( "Resistance: " + resistances.get(dt));
		return damageAmount - (damageAmount * (resistances.get(dt) / 100f));
	}
	
	
	@Override
	public String toString() {
		return this.resistances.toString();
	}
	
	
	
}
