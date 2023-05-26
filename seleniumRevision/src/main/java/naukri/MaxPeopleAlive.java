package naukri;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Person {
	private int birth_year;
	public int getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(int birth_year) {
		this.birth_year = birth_year;
	}
	private int end_year;
	public int getEnd_year() {
		return end_year;
	}
	public void setEnd_year(int end_year) {
		this.end_year = end_year;
	}
}

public class MaxPeopleAlive {
	final int Min_Birth_year = 1850, Max_Birth_year=2000;
	final int Max_End_year=2021, Min_End_year=1900;
	final int Max_Life_Span = 100;
		
	public static void main(String[] args) {
		MaxPeopleAlive mpa = new MaxPeopleAlive();
		int max_records= 10000;
		List<Person> test_data = mpa.createData(max_records);
		
		System.out.println(" Maximum Number of People Alive were in years : " +mpa.findYearsWithMaxPopulation(test_data));
	}
	
	private List<Integer> findYearsWithMaxPopulation(List<Person> test_data){
		List<Integer> count = new ArrayList<Integer>();
		Map<Integer, Integer> years = new TreeMap<Integer, Integer>();
		for(Person person : test_data) {
			int DOB = 0;
			int DOE = 0;
			if(!(person.getBirth_year()==0 && person.getEnd_year() ==0)) {
				if(person.getBirth_year()==0)
					DOB = person.getEnd_year()- Max_Life_Span;
				else 
					DOB =  person.getBirth_year();
				
				if(person.getEnd_year()==0)
					DOE = person.getBirth_year() + Max_Life_Span ; 
				else
					DOE = person.getEnd_year();
				
				if(DOB > Min_End_year && DOE< Max_End_year ) {
				if(!years.containsKey(DOB)) {
					years.put(DOB, 0);
				}
				years.put(DOB, years.get(DOB) + 1);
				if(!years.containsKey(DOE+1)) {
					years.put(DOE+1, 0);
				}
				years.put(DOE+1, years.get(DOE+1) - 1);
				
			}
			}
		}
		System.out.println(years);
		int maxAlive = Collections.max(years.values());
		for(Map.Entry<Integer, Integer> entry : years.entrySet()) {
			if(entry.getValue() == maxAlive && entry.getKey() > Min_End_year ) {
				count.add(entry.getKey());
			}			
		}
		
		return count;
	}

		private List<Person> createData(int max_records){
		List<Person> persons = new ArrayList<Person>();
		for(int i=0;i<max_records;i++) {
			Person person = new Person();
			int DOB= getRandomNumber(Min_Birth_year,Max_Birth_year);
			int DOE= getRandomNumber(DOB,Max_End_year);
			person.setBirth_year(DOB);
			person.setEnd_year(DOE);
			persons.add(person);
		}
		for(int i=0;i<10;i++) {
			Person person = new Person();
			int DOB= 0;
			int DOE= getRandomNumber(Min_End_year,Max_End_year);
			person.setBirth_year(DOB);
			person.setEnd_year(DOE);
			persons.add(person);
		}
		for(int i=0;i<10;i++) {
			Person person = new Person();
			int DOB= getRandomNumber(Min_Birth_year,Max_Birth_year);
			int DOE= 0;
			person.setBirth_year(DOB);
			person.setEnd_year(DOE);
			persons.add(person);
		}
		for(int i=0;i<10;i++) {
			Person person = new Person();
			int DOB= 0;
			int DOE= 0;
			person.setBirth_year(DOB);
			person.setEnd_year(DOE);
			persons.add(person);
		}
		return persons;
	}
	private int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}

}
