/* Date.java */

import java.io.*;

class Date {

  /* Put your private data fields here. */
	private int date_month;
	private int date_day;
	private int date_year;

  /** Constructs a date with the given month, day and year.   If the date is
   *  not valid, the entire program will halt with an error message.
   *  @param month is a month, numbered in the range 1...12.
   *  @param day is between 1 and the number of days in the given month.
   *  @param year is the year in question, with no digits omitted.
   */
  public Date(int month, int day, int year) {
	  if(isValidDate(month, day, year)) {
		  date_month = month;
		  date_day = day;
		  date_year = year;
	  } else {
		  System.out.println("Date you entered is invalid!");
		  System.exit(0);
	  }

  }

  /** Constructs a Date object corresponding to the given string.
   *  @param s should be a string of the form "month/day/year" where month must
   *  be one or two digits, day must be one or two digits, and year must be
   *  between 1 and 4 digits.  If s does not match these requirements or is not
   *  a valid date, the program halts with an error message.
   */
  public Date(String s) {
	  String[] date_split = s.split("/");
	  if((date_split[0].length() > 2 || date_split[0].length() <= 0 ) || (date_split[1].length() > 2 || date_split[0].length() <= 0) || (date_split[2].length() > 4 || date_split[0].length() <= 0)) {
		  System.out.println("Date is invalid!");
		  System.exit(0);
	  }
	  int date_split_month = Integer.parseInt(date_split[0]);
	  int date_split_day = Integer.parseInt(date_split[1]);
	  int date_split_year = Integer.parseInt(date_split[2]);
	  if(isValidDate(date_split_month, date_split_day, date_split_year)) {
		  date_month = date_split_month;
		  date_day = date_split_day;
		  date_year = date_split_year;
	  } else {
		  System.out.println("Date you entered is invalid!");
		  System.exit(0);
	  }
	  

  }

  /** Checks whether the given year is a leap year.
   *  @return true if and only if the input year is a leap year.
   */
  public static boolean isLeapYear(int year) {
	  if(year % 4 == 0) {
		  if(year % 100 == 0) {
			  if(year % 400 ==0) {
				  return true;
			  }
			  return false;
		  }
		  return true;
	  }
	  return false;	// replace this line with your solution
  }

  /** Returns the number of days in a given month.
   *  @param month is a month, numbered in the range 1...12.
   *  @param year is the year in question, with no digits omitted.
   *  @return the number of days in the given month.
   */
  public static int daysInMonth(int month, int year) {
	  switch(month) {
	  case 2:
		  if(isLeapYear(year)) {
			  return 29;
		  }
		  return 28;
	  case 4:
	  case 6:
	  case 9:
	  case 11:
		  return 30;
	  default:
		  return 31;
	  }
                            // replace this line with your solution
  }

  /** Checks whether the given date is valid.
   *  @return true if and only if month/day/year constitute a valid date.
   *
   *  Years prior to A.D. 1 are NOT valid.
   */
  public static boolean isValidDate(int month, int day, int year) {
	  if(year > 0){
		  if(month > 0 && month < 13) {
			  if(day <= daysInMonth(month, year) && day > 0) {
				  return true;
			  }
			  return false;
		  }
		  return false;
	  }
	  return false;
                           // replace this line with your solution
  }

  /** Returns a string representation of this date in the form month/day/year.
   *  The month, day, and year are expressed in full as integers; for example,
   *  12/7/2006 or 3/21/407.
   *  @return a String representation of this date.
   */
  public String toString() {
	  return (date_month + "/" + date_day + "/" + date_year);
                         // replace this line with your solution
  }

  /** Determines whether this Date is before the Date d.
   *  @return true if and only if this Date is before d. 
   */
  public boolean isBefore(Date d) {
	  if(date_year < d.date_year){
		  return true;
	  } else if(date_year == d.date_year){
		  if(date_month < d.date_month){
			  return true;
		  } else if(date_month == d.date_month) {
			  if(date_day < d.date_day){
				  return true;
			  }
			  return false;
		  } else {
			  return false;  
		  }
	  } else {
		  return false;
	  }
                            // replace this line with your solution
  }

  /** Determines whether this Date is after the Date d.
   *  @return true if and only if this Date is after d. 
   */
  public boolean isAfter(Date d) {
	  if(isBefore(d) || isSame(d)){
		  return false;
	  } 
	  return true;
	  // replace this line with your solution
  }
  
  public boolean isSame(Date d) {
	  if(date_year == d.date_year && date_month == d.date_month && date_day == d.date_day) {
		  return true;
	  }
	  return false;
  }

  /** Returns the number of this Date in the year.
   *  @return a number n in the range 1...366, inclusive, such that this Date
   *  is the nth day of its year.  (366 is used only for December 31 in a leap
   *  year.)
   */
  public int dayInYear() {
	  int days = 0;
	  //count how many days before this month
	  for(int i = 1; i < date_month; i++){
		  days += daysInMonth(i, date_year);
	  }
	  return days + date_day;
                              // replace this line with your solution
  }

  /** Determines the difference in days between d and this Date.  For example,
   *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
   *  If this Date occurs before d, the result is negative.
   *  @return the difference in days between d and this date.
   */
  public int difference(Date d) {
	  if(isSame(d)) {
		  return 0;
	  } else if(isAfter(d)){
		  return this.difference_year(d);
	  } else {
		  return -d.difference_year(this);
	  }
                               // replace this line with your solution
  }
  /**
   * 
   * @param d
   * @return the difference in days between d and this date. if these two dates are different.
   */
  public int difference_year(Date d) {
	  int days_diff_year = 0;
	  boolean isBeforeFeb29 = false;
	  if(isLeapYear(d.date_year)) {
		  Date feb_29_thisYear = new Date(2, 29, d.date_year);
		  if(d.isBefore(feb_29_thisYear)) {
			  isBeforeFeb29 = true;
		  }
	  }
	  for(int i = 1; i <= date_year - d.date_year; i++){
		  if(isLeapYear(d.date_year + i) ||  isBeforeFeb29){
			  days_diff_year += 366;
		  	} else {
		  		days_diff_year += 365;
			}
	  }
	  Date same_year = new Date(d.date_month, d.date_day, date_year);
	  return this.dayInYear() - same_year.dayInYear() + days_diff_year;
  }
 
  public static void main(String[] argv) {
    System.out.println("\nTesting constructors.");
    Date d1 = new Date(1, 1, 1);
    System.out.println("Date should be 1/1/1: " + d1);
    d1 = new Date("2/4/2");
    System.out.println("Date should be 2/4/2: " + d1);
    d1 = new Date("2/29/2000");
    System.out.println("Date should be 2/29/2000: " + d1);
    d1 = new Date("2/29/1904");
    System.out.println("Date should be 2/29/1904: " + d1);

    d1 = new Date(12, 31, 1975);
    System.out.println("Date should be 12/31/1975: " + d1);
    Date d2 = new Date("1/1/1976");
    System.out.println("Date should be 1/1/1976: " + d2);
    Date d3 = new Date("1/2/1976");
    System.out.println("Date should be 1/2/1976: " + d3);

    Date d4 = new Date("2/27/1977");
    Date d5 = new Date("8/31/2110");

    /* I recommend you write code to test the isLeapYear function! */

    System.out.println("\nTesting before and after.");
    System.out.println(d2 + " after " + d1 + " should be true: " + 
                       d2.isAfter(d1));
    System.out.println(d3 + " after " + d2 + " should be true: " + 
                       d3.isAfter(d2));
    System.out.println(d1 + " after " + d1 + " should be false: " + 
                       d1.isAfter(d1));
    System.out.println(d1 + " after " + d2 + " should be false: " + 
                       d1.isAfter(d2));
    System.out.println(d2 + " after " + d3 + " should be false: " + 
                       d2.isAfter(d3));

    System.out.println(d1 + " before " + d2 + " should be true: " + 
                       d1.isBefore(d2));
    System.out.println(d2 + " before " + d3 + " should be true: " + 
                       d2.isBefore(d3));
    System.out.println(d1 + " before " + d1 + " should be false: " + 
                       d1.isBefore(d1));
    System.out.println(d2 + " before " + d1 + " should be false: " + 
                       d2.isBefore(d1));
    System.out.println(d3 + " before " + d2 + " should be false: " + 
                       d3.isBefore(d2));

    System.out.println("\nTesting difference.");
    System.out.println(d1 + " - " + d1  + " should be 0: " + 
                       d1.difference(d1));
    System.out.println(d2 + " - " + d1  + " should be 1: " + 
                       d2.difference(d1));
    System.out.println(d3 + " - " + d1  + " should be 2: " + 
                       d3.difference(d1));
    System.out.println(d3 + " - " + d4  + " should be -422: " + 
                       d3.difference(d4));
    System.out.println(d5 + " - " + d4  + " should be 48762: " + 
                       d5.difference(d4));
  }
}
