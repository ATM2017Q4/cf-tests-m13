package com.cheapflights.ui.entities;

public class TravelInfo {
    private String origin;
    private String destination;
    private int numberOfAdults;
    private DepartureDates departureDates;
    private ReturnDates returnDates;
    private int acceptablePrice;


    public DepartureDates getDepartureDates() {
        return departureDates;
    }

    public ReturnDates getReturnDates() {
        return returnDates;
    }


    public String getOrigin() {
        return origin;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }


    public String getDestination() {
        return destination;
    }


    public int getAcceptablePrice() {
        return acceptablePrice;
    }


    public static class DepartureDates {
        private int day;
        private String month;
        private int year;

        public int getDay() {
            return day;
        }


        public String getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public static class ReturnDates {
        private int day;
        private String month;
        private int year;

        public int getDay() {
            return day;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

}
