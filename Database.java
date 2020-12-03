import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Databases class for managing the backend.
 * 
 * @author Noah Linton, Ahmad Osmani
 * (Modified by JazzyLucas)
 */

public class Database {

    public static String URL = "jdbc:mysql://cisvm-winsrv-mysql1.unfcsd.unf.edu:3306/group6";
    public static String username = "n01421210";
    public static String password = "Fall20201210"; //password
    
    // Added a global boolean for working with the project locally.
    // Changes were made accordingly. ~Lucas
    public static boolean isOffline = false;
    public static int demoInt = 0;

    public static void main(String[] args) throws Exception {    
    }

    //Start of user functions
    /*
     * user must put in their userID
     * otherwise they can proceed as a guest and their info will be stored
     * when they create/request a new lease
     */
    public static boolean Login(String userlogin, String userPassword) throws Exception {
    	if (isOffline) {
    		return true;
    	}
        String userEmailGUI = "";
        userEmailGUI = userEmailGUI.concat("\"" + userlogin + "\"");
        String loginQuery = "select user.Email, user.password, user.isAdmin from user where user.Email = ";
        String supportloginQuery = loginQuery.concat(userEmailGUI);

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(supportloginQuery);

        ArrayList<String> myarray = new ArrayList<>();
        while (result.next() != false) {
            myarray.add(result.getString("Email"));
            myarray.add(result.getString("password"));
            myarray.add(result.getString("isAdmin"));
        }
        try {
            if (userlogin.equals(myarray.get(0)) && userPassword.equals(myarray.get(1))) {
                System.out.println("login successful");
                System.out.println(myarray.get(2));
                return true;
            }
        } catch (Exception e) {
        }
        st.close();
        con.close();
        return false;
    }

    //registers a new user
    public static void Registration(ArrayList<String> userInfo) throws Exception {
    	if (isOffline) {
    		return;
    	}
        String addUser = "INSERT INTO `group6`.`user` (`FirstName`, `LastName`, `PhoneNumber`, `Email`, `City`, `State`, `Zip`, `StreetAddress`, `password`) VALUES ("
                + "\"" + userInfo.get(0) + "\", " + "\"" + userInfo.get(1) + "\", " + "\"" + userInfo.get(2) + "\", " + "\"" + userInfo.get(3) + "\", " + "\"" + userInfo.get(4) +
                "\", " + "\"" + userInfo.get(5) + "\", " + "\"" + userInfo.get(6) + "\", " + "\"" + userInfo.get(7) + "\", " + "\"" + userInfo.get(8) + "\")";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(addUser);
    }

    //    /*
//     * search for apartments/houses
//     * #of bedrooms, price, location, amenities
//     * have various qualifiers that the user can choose from that will
//     * exclude other results of the search based on their metadata
//     * as listed above
//     */
    public static ArrayList<String> Search(int numbedrooms, int numbathrooms, double maxPrice, int houseType) throws Exception {
    	if (isOffline) {
    		ArrayList<String> offlineResult = new ArrayList<String>();
    		offlineResult.add("000001");
    		offlineResult.add("000002");
    		offlineResult.add("000003");
    		offlineResult.add("000004");
    		offlineResult.add("000005");
    		offlineResult.add("000006");
    		return offlineResult;
    	}
        //show all listing if nothing is selected
        String query = "";
        boolean flag = false;
        if (houseType == 0) {
            query = "select * from apartment where ";
            if (numbedrooms > 0) {
                String qbed = "apartment.Bedrooms = " + "\"" + numbedrooms + "\"";
                query = query.concat(qbed);
                flag = true;
            }
            if (numbathrooms > 0) {
                String qbath = "apartment.Bathrooms = " + "\"" + numbathrooms + "\"";
                if (flag) {
                    query = query.concat(" AND ");
                }
                query = query.concat(qbath);
                flag = true;
            }
            if (maxPrice > 0) {
                String qprice = "apartment.Price <= " + "\"" + maxPrice + "\"";
                if (flag) {
                    query = query.concat(" AND ");
                }
                query = query.concat(qprice);
                flag = true;
            }
            if (flag) {
                query = query.concat("AND isLeased = 0");
            }
            if (!flag) {
                query = "select * from apartment where isleased = 0";
            }
        }
        //if the select houseType is a house
        else if (houseType == 1) {
            query = "select * from house where ";
            if (numbedrooms > 0) {
                String qbed = "house.Bedrooms = " + "\"" + numbedrooms + "\"";
                query = query.concat(qbed);
                flag = true;
            }
            if (numbathrooms > 0) {
                String qbath = "house.Bathrooms = " + "\"" + numbathrooms + "\"";
                if (flag) {
                    query = query.concat(" AND ");
                }
                query = query.concat(qbath);
                flag = true;
            }
            if (maxPrice > 0) {
                String qprice = "house.Price <= " + "\"" + maxPrice + "\"";
                if (flag) {
                    query = query.concat(" AND ");
                }
                query = query.concat(qprice);
                flag = true;
            }
            if (flag) {
                query = query.concat("AND isLeased = 0");
            }
            if (!flag) {
                query = "select * from house where isleased = 0";
            }
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);

        ArrayList<String> myarray = new ArrayList<>();
        if (houseType == 0) {
            while (result.next() != false) {
                myarray.add(result.getString("ApartmentID"));
            }
        } else if (houseType == 1) {
            while (result.next() != false) {
                myarray.add(result.getString("HouseID"));
            }
        }
        st.close();
        con.close();
        return myarray;
    }
    
    public static ArrayList<String> propertyDetails(int apartmentid, int houseid) throws Exception {
    	if (isOffline) {
    		ArrayList<String> offlineResult = new ArrayList<>();
    		demoInt++;
    		switch (demoInt) {
			case 1:
				offlineResult.add("000001");offlineResult.add("Beachwood Apartments");offlineResult.add("5");offlineResult.add("2");offlineResult.add("3");offlineResult.add("2");offlineResult.add("1200.00");offlineResult.add("All");
				break;
			case 2:
				offlineResult.add("000002");offlineResult.add("Beachwood Apartments");offlineResult.add("3");offlineResult.add("6");offlineResult.add("3");offlineResult.add("1");offlineResult.add("1200.00");offlineResult.add("All");
				break;
			case 3:
	    		offlineResult.add("000003");offlineResult.add("Beachwood Apartments");offlineResult.add("12");offlineResult.add("2");offlineResult.add("2");offlineResult.add("1");offlineResult.add("1200.00");offlineResult.add("All");
				break;
			case 4:
				offlineResult.add("000004");offlineResult.add("Beachwood Apartments");offlineResult.add("11");offlineResult.add("1");offlineResult.add("3");offlineResult.add("1");offlineResult.add("1200.00");offlineResult.add("All");
				break;
			case 5:
				offlineResult.add("000005");offlineResult.add("Beachwood Apartments");offlineResult.add("2");offlineResult.add("8");offlineResult.add("2");offlineResult.add("1");offlineResult.add("1200.00");offlineResult.add("All");
				break;
			case 6:
	    		offlineResult.add("000006"); offlineResult.add("Beachwood Apartments"); offlineResult.add("7"); offlineResult.add("8"); offlineResult.add("3"); offlineResult.add("2"); offlineResult.add("1200.00"); offlineResult.add("All");
				demoInt = 0;
	    		break;
    		}
    		return offlineResult;
    	}
        String query = "";
        ArrayList<String> pDetails = new ArrayList<>();
        if (apartmentid > 0) {
            query = "select apartment.ApartmentID, apartment.ApartmentNumber, apartment.BuildingID, apartment.Bedrooms, " +
                    "apartment.Bathrooms, apartment.Price, apartment.LeaseOptions, location.LocationName from apartment join location " +
                    "on apartment.LocationID = location.LocationID where apartment.ApartmentID = " + "\"" + apartmentid + "\"";
            System.out.println(query);
        } else if (houseid > 0) {
            query = "select house.HouseID, house.Bedrooms, house.Bathrooms, house.Price, house.LeaseOptions" +
                    ", location.LocationName, location.Neighborhood from house join location on location.LocationID = house.LocationID " +
                    "where house.HouseID = " + "\"" + houseid + "\"";
            System.out.println(query);
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        result.next();

        if (apartmentid > 0) {
            pDetails.add(result.getString("ApartmentID"));
            pDetails.add(result.getString("LocationName"));
            pDetails.add(result.getString("ApartmentNumber"));
            pDetails.add(result.getString("BuildingID"));
            pDetails.add(result.getString("Bedrooms"));
            pDetails.add(result.getString("Bathrooms"));
            pDetails.add(result.getString("Price"));
            pDetails.add(result.getString("LeaseOptions"));


        } else if (houseid > 0) {
            pDetails.add(result.getString("HouseID"));
            pDetails.add(result.getString("LocationName"));
            pDetails.add(result.getString("Neighborhood"));
            pDetails.add(result.getString("Bedrooms"));
            pDetails.add(result.getString("Bathrooms"));
            pDetails.add(result.getString("Price"));
            pDetails.add(result.getString("LeaseOptions"));
        }
        st.close();
        con.close();
        return pDetails;
    }

    //
//    // view houses and apartment facilities and recommendation
//    // after seeing the house the information about it will be displayed
    //might be used to show amenities for an individual apartment/house
    public static ArrayList<String> ViewHA(int apartmentid, int houseid) throws Exception {
    	if (isOffline) {
    		ArrayList<String> offlineResult = new ArrayList<>();
    		offlineResult.add("Laundry");
    		offlineResult.add("Parking");
    		offlineResult.add("HardwoodFloors");
    		offlineResult.add("Dishwasher");
    		offlineResult.add("AirConditioner");
    		offlineResult.add("Patio");
    		offlineResult.add("Gym");
    		offlineResult.add("Pool");
    		offlineResult.add("PetsAllowed");
    		offlineResult.add("GarbageDisposal");
    		offlineResult.add("Refrigerator");
    		return offlineResult;
    	}
        String query = "";
        if(apartmentid > 0){
            query = "select apartment.LocationID from apartment where apartment.ApartmentID = " + "\"" + apartmentid + "\"";
            System.out.println(query);
        }
        if(houseid >0){
            query = "select house.LocationID from house where house.HouseID = " + "\"" + houseid + "\"";
            System.out.println(query);
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        result.next();
        String locationID = result.getString("LocationID");
        String Amenities = "select * from amenities where amenities.LocationID = " + "\"" + locationID +"\"";
        result = st.executeQuery(Amenities);
        ArrayList<String> myarray = new ArrayList<>();
        while (result.next() != false) {
            myarray.add(result.getString("Laundry"));
            myarray.add(result.getString("Parking"));
            myarray.add(result.getString("HardwoodFloors"));
            myarray.add(result.getString("Dishwasher"));
            myarray.add(result.getString("AirConditioner"));
            myarray.add(result.getString("Patio"));
            myarray.add(result.getString("Gym"));
            myarray.add(result.getString("Pool"));
            myarray.add(result.getString("PetsAllowed"));
            myarray.add(result.getString("GarbageDisposal"));
            myarray.add(result.getString("Refrigerator"));
        }
        try {
            for (int j = 0; !myarray.isEmpty(); j++) {
                System.out.println(myarray.get(j));
            }
        } catch (Exception e) {

        }
        st.close();
        con.close();
        return myarray;
    }

    /*
     * requests a new lease contract
     * if the lease is accepted they will recieve a contract
     * with duration and cost
     * send something like your request is being reviewed
     * or autogenerate a contract with given conditions
     * have given qualifiers for automatic accept
     */
    public static void applicationRequest(int userid, ArrayList<Integer> roommates, int userIncome, int ApartmentId, int HouseID, String LeaseOption) throws Exception {
    	if (isOffline ) {
    		System.out.println("Sent in an application request.");
    		return;
    	}
    	String query = "";
        if(ApartmentId > 0){
           query = "INSERT INTO `group6`.`application` (`MainUserID`, `Income`, `ApartmentID`, `LeaseOption`) VALUES ("
                    + "\"" + userid + "\", " + "\"" + userIncome + "\", " + "\"" + ApartmentId + "\", " + "\"" + LeaseOption + "\")";
        }
        if(HouseID > 0){
            query = "INSERT INTO `group6`.`application` (`MainUserID`, `Income`, `HouseID`, `LeaseOption`) VALUES ("
                    + "\"" + userid + "\", " + "\"" + userIncome + "\", " + "\"" + HouseID + "\", " + "\"" + LeaseOption + "\")";
        }
        System.out.println(query);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(query);
        st.close();
        con.close();
        insertRoommatesApp(roommates, userid, ApartmentId, HouseID);
    }

    public static void insertRoommatesApp(ArrayList<Integer> roommates, int mainuser, int apartmentid, int houseid) throws Exception {
    	String query = "";
        if (apartmentid > 0) {
            query = "select application.ApplicationID from application where application.MainUserID = " + "\"" + mainuser + "\"" + " AND application.ApartmentID = " + "\"" + apartmentid + "\"";
        }
        if (houseid > 0) {
            query = "select application.ApplicationID from application where application.MainUserID = " + "\"" + mainuser + "\"" + " AND application.HouseID = " + "\"" + houseid + "\"";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        result.next();
        String appID = result.getString("ApplicationID");
        String Updatequery = "";
        try {
            for (int i = 0; !roommates.isEmpty(); i++) {
                Updatequery = "INSERT INTO `group6`.`application_has_user` (`ApplicationID`, `UserID`) VALUES (" + "\"" + appID + "\", " + "\"" + roommates.get(i) + "\")";
                int count = st.executeUpdate(Updatequery);
            }
        }catch(Exception e){

        }
        st.close();
        con.close();
    }

    public static ArrayList<String> viewAllApplications() throws Exception {
    	if (isOffline) {
    		ArrayList<String> offlineResult = new ArrayList<>();
    		offlineResult.add("000069");
    		offlineResult.add("001234");
    		offlineResult.add("1000000.00");
    		offlineResult.add("000123");
    		offlineResult.add("000002");
    		offlineResult.add("Approved");
    		offlineResult.add("All");
    		return offlineResult;
    	}
    	String query = "select * from application where application.ApplicationStatus is null";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        ArrayList<String> myarray = new ArrayList<>();

        while (result.next() != false) {
            myarray.add(result.getString("ApplicationID"));
            myarray.add(result.getString("MainUserID"));
            myarray.add(result.getString("Income"));
            myarray.add(result.getString("ApartmentID"));
            myarray.add(result.getString("HouseID"));
            myarray.add(result.getString("ApplicationStatus"));
            myarray.add(result.getString("LeaseOption"));
        }
        st.close();
        con.close();
        return myarray;
    }

    /*
     * Function be ran when lease is accepted
     * Must have: Monthly cost, Lease Duration, late rental penalty
     * Early Termination penalty
     * gets userid and updates their records to provide a lease history
     * pushes this data into sql file
     * default contract duration would be 12 months
     */
    public static void createContract(int userid, int apartmentid, int houseid, double cost) throws Exception {
        //Late rent penalty would be 10% of their original rent amount
        double rentPenalty = 0.1 * cost;
        //Early termination penalty would be extra two months rent
        double EarlyTPenalty = 2 * cost;
        String duration = "12 Months";
        String query = "";
        if(apartmentid > 0) {
            query = "INSERT INTO `group6`.`contract` (`Duration`, `Cost`, `UserID`, `ApartmentID`, `LateRentPenalty`, `EarlyTpenalty`) VALUES ("
                    + "\"" + duration + "\", " + "\"" + cost + "\", " + "\"" + userid + "\", " + "\"" + apartmentid + "\", " + "\"" + rentPenalty + "\", " + "\"" + EarlyTPenalty + "\");";
        }
        if(houseid > 0){
            query = "INSERT INTO `group6`.`contract` (`Duration`, `Cost`, `UserID`, `HouseID`, `LateRentPenalty`, `EarlyTpenalty`) VALUES ("
                    + "\"" + duration + "\", " + "\"" + cost + "\", " + "\"" + userid + "\", " + "\"" + houseid + "\", " + "\"" + rentPenalty + "\", " + "\"" + EarlyTPenalty + "\");";
        }
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(query);
        st.close();
        con.close();
    }

    /*
     * takes user id and displays the contents of the file that stores
     * how much payment is left in their lease (leaseamount - payments)
     * show each apartments seperate amount due then display total
     * if they own multiple apartments
     */
    public static ArrayList<String> CheckDues(int UserID) throws Exception {
    	if (isOffline) {
    		ArrayList<String> offlineResult = new ArrayList<>();
    		offlineResult.add("000001"); offlineResult.add("Monthly"); offlineResult.add("Paid"); offlineResult.add("0.00"); offlineResult.add(UserID + ""); 
    		offlineResult.add("000002"); offlineResult.add("Monthly"); offlineResult.add("Paid"); offlineResult.add("0.00"); offlineResult.add(UserID + ""); 
    		offlineResult.add("000003"); offlineResult.add("Monthly"); offlineResult.add("Unpaid"); offlineResult.add("350.00"); offlineResult.add(UserID + ""); 
    		offlineResult.add("000004"); offlineResult.add("Monthly"); offlineResult.add("Paid"); offlineResult.add("0.00"); offlineResult.add(UserID + ""); 
    		offlineResult.add("000005"); offlineResult.add("Monthly"); offlineResult.add("Paid"); offlineResult.add("0.00"); offlineResult.add(UserID + ""); 
    		return offlineResult;
    	}
        String query = "select * from BILL WHERE bill.UserID =" + "\"" + UserID + "\"" + "AND bill.PaymentStatus = 0";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);

        ArrayList<String> myarray = new ArrayList<>();
        while (result.next() != false) {
            myarray.add(result.getString("BillID"));
            myarray.add(result.getString("BillType"));
            myarray.add(result.getString("PaymentStatus"));
            myarray.add(result.getString("PenaltyFee"));
            myarray.add(result.getString("UserID"));
            myarray.add(result.getString("Amount"));
            myarray.add(result.getString("ApartmentID"));
            myarray.add(result.getString("HouseID"));
            //once 6 entries are sent it is considered a new line and should be treated as such
        }
//        try {
//            for (int j = 0; !myarray.isEmpty(); j++) {
//
//                System.out.println(myarray.get(j));
//            }
//        } catch (Exception e) {
//        }
//        int j = 0;

        st.close();
        con.close();
        return myarray;

    }


    /*
     * must run checkdues before
     * the user puts in the amount of money they would like to pay
     * towards their current lease (must select which house if
     * multiple houses exists on their account) then subtract that
     * amount from their currents
     */
    public static void PayDues(int BillID) throws Exception {
        if (isOffline) {
        	System.out.println("Payment sent successfully.");
        	return;
        }
        String query = "UPDATE `group6`.`bill` SET `PaymentStatus` = '1' WHERE (`BillID` = " + "\"" + BillID + "\")";
        System.out.println(query);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(query);
        st.close();
        con.close();
    }

    /*
     * allow the user to select a # 1-5 on a specific apartment/house
     * note: could string whatever the user wants to put into the review
     * then upload that under their renter id
     */
    public static void AddReview(int apartmentid, int houseid, int score, int userid) throws Exception {
    	if (isOffline) {
    		System.out.println("Review sent successfully.");
    		return;
    	}
    	// add Recommendation on canvas
        String addquery = "";
        String supportQuery = "";
        if (houseid > 0) {
            addquery = "INSERT INTO `group6`.`review` (`Score`, `UserID`, `HouseID`) VALUES (";
            supportQuery = "'" + score + "', " + "'" + userid + "', " + "'" + houseid + "' )";
            addquery = addquery.concat(supportQuery);
            System.out.println(addquery);
        } else if (apartmentid > 0) {
            addquery = "INSERT INTO `group6`.`review` (`Score`, `UserID`, `ApartmentID`) VALUES (";
            supportQuery = "'" + score + "', " + "'" + userid + "', " + "'" + apartmentid + "' )";
            addquery = addquery.concat(supportQuery);
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(addquery);
        st.close();
        con.close();
    }

    public static void showReview(int apartmentid, int houseid) throws Exception {
        String query = "";
        String supportQuery = "";
        if (apartmentid > 0) {
            query = "select Review.Score, Review.UserID, Review.ApartmentID from Review where Review.ApartmentID = ";
            supportQuery = "\"" + apartmentid + "\"";
            query = query.concat(supportQuery);
            System.out.println(query);
        } else if (houseid > 0) {
            query = "select Review.Score, Review.UserID, Review.HouseID from Review where Review.HouseID = ";
            supportQuery = "\"" + houseid + "\"";
            query = query.concat(supportQuery);
            System.out.println(query);
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);

        ArrayList<String> myarray = new ArrayList<>();
        while (result.next() != false) {
            myarray.add(result.getString("Score"));
        }

        try {
            for (int j = 0; !myarray.isEmpty(); j++) {

                System.out.println(myarray.get(j));
            }
        } catch (Exception e) {
            System.out.print("");
        }
        //return the average score for an apartment
        st.close();
        con.close();
    }

    /*
     * Allow user to end their contract early with a penalty
     * runs the checkdues function to check if they owe anything
     * then instances a penalty based on the amount of rent left
     * whatever 2 months of rent is the penalty to cancel
     */
    public static void REarlyT(int userid, int apartmentid, int houseid) throws Exception {
    	if (isOffline) {
    		System.out.println("Early terminated a contract.");
    		return;
    	}
        String query = "";
        if(apartmentid > 0){
            query = "select lease.LeaseID from lease where lease.MainUserID = " + "\"" + userid + "\"" +  " AND lease.ApartmentID = " + "\"" + apartmentid + "\"";
        }
        if(houseid > 0){
            query = "select lease.LeaseID from lease where lease.MainUserID = " + "\"" + userid + "\"" +  " AND lease.HouseID = " + "\"" + houseid + "\"";
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        result.next();
        String leaseID = result.getString("LeaseID");
        String updateQuery = "UPDATE `group6`.`lease` SET `Status` = '0' WHERE (`LeaseID` = " + "\"" + leaseID + "\")";
        int count = st.executeUpdate(updateQuery);
        st.close();
        con.close();
        terminateContract(userid,apartmentid,houseid);
    }
    public static void terminateContract(int userid, int apartmentid, int houseid) throws Exception {
    	if (isOffline) {
    		System.out.println("Terminated a contract.");
    		return;
    	}
        String query = "";
        if(apartmentid > 0){
            query = "select contract.ContractID, contract.EarlyTPenalty from contract where contract.UserID = " + "\"" + userid + "\"" +
                    " AND contract.ApartmentID = " + "\"" + apartmentid + "\"";
        }
        if(houseid > 0){
            query = "select contract.ContractID, contract.EarlyTPenalty from contract where contract.UserID = " + "\"" + userid + "\"" +
                    " AND contract.HouseID = " + "\"" + houseid + "\"";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        result.next();
        String contractid = result.getString("ContractID");
        Double EarlyTPenalty = Double.parseDouble(result.getString("EarlyTPenalty"));
        String contractStatus = "UPDATE `group6`.`contract` SET `Status` = '0' WHERE (`ContractID` = " + "\"" + contractid + "\")";
        int count  = st.executeUpdate(contractStatus);
        CreateBill(userid, "Early Termination Fee", EarlyTPenalty, apartmentid, houseid);

    }
    /*
     * end of user functions
     * Start of Admin Functions
     */

    /*
     * creates a lease and a contract
     * the lease telling the user the amount due per month, how
     * long the lease will last for along with various bills
     *
     * the contract sent to the user asking them to sign before they
     * can begin moving in and making payments
     */
    public static void CLease(int userid, int apartmentid, int houseid, ArrayList<Integer> roommates, String LeaseOption) throws Exception {
    	if (isOffline) {
    		System.out.println("Created a lease.");
    		return;
    	}
        /*
         *  if the bill is not paid within 5 days from the end or
         *  if the bill is late there will be a penalty added to the bill
         *  if the user does not pay rent for 3 months they can be early-terminated
         *  and have to pay early termination fees
         */
        String query = "" ;
        if(apartmentid > 0){
            query = "select apartment.Price from apartment where apartment.ApartmentID = " + "\"" + apartmentid + "\"";

        }
        if(houseid > 0){
            query = "select house.Price from house where house.HouseID = " + "\"" + houseid + "\"";

        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        result.next();
        String price = "";
        price = result.getString("Price");

        String Leaseadd = "";
        String isLeased ="";
        if(apartmentid > 0){
            Leaseadd = "INSERT INTO `group6`.`lease` (`LeaseOption`, `ApartmentID`, `rentAmount`, `MainUserID`) VALUES ("
                    + "\"" + LeaseOption + "\", " + "\"" + apartmentid + "\", " + "\"" + price + "\", " + "\"" + userid + "\")";
            isLeased = "UPDATE `group6`.`apartment` SET `isLeased` = '1' WHERE (`ApartmentID` = " + "\"" + apartmentid + "\")";

        }
        if(houseid > 0){
            Leaseadd = "INSERT INTO `group6`.`lease` (`LeaseOption`, `HouseID`, `rentAmount`, `MainUserID`) VALUES ("
                    + "\"" + LeaseOption + "\", " + "\"" + houseid + "\", " + "\"" + price + "\", " + "\"" + userid + "\")";
            isLeased = "UPDATE `group6`.`house` SET `isLeased` = '1' WHERE (`HouseID` = " + "\"" + houseid + "\")";
        }
        int count = st.executeUpdate(Leaseadd);
        insertRoommatesLease(userid, apartmentid, houseid, roommates);
        double cost = Double.parseDouble(price);
        createContract(userid,apartmentid,houseid,cost);
        CreateBill(userid,"rent",cost, apartmentid, houseid);
        int count1 = st.executeUpdate(isLeased);
        st.close();
        con.close();

    }

    public static void insertRoommatesLease(int mainuserid, int apartmentid, int houseid, ArrayList<Integer> roommates) throws Exception{
        String query = "";
        if (apartmentid > 0) {
            query = "select lease.LeaseID from lease where lease.MainUserID = " + "\"" + mainuserid + "\"" + " AND lease.ApartmentID = " + "\"" + apartmentid + "\"";
        }
        if (houseid > 0) {
            query = "select lease.LeaseID from lease where lease.MainUserID = " + "\"" + mainuserid + "\"" + " AND lease.HouseID = " + "\"" + houseid + "\"";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        result.next();
        String LeaseID = result.getString("LeaseID");
        String Updatequery = "";
        try {
            for (int i = 0; !roommates.isEmpty(); i++) {
                Updatequery = "INSERT INTO `group6`.`lease_has_user` (`LeaseID`, `UserID`) VALUES (" + "\"" + LeaseID + "\", " + "\"" + roommates.get(i) + "\")";
                int count = st.executeUpdate(Updatequery);
            }
        }catch(Exception e){

        }
        st.close();
        con.close();
    }

    //This function Creates a new bill for a user
    public static void CreateBill(int UserID, String BillType, Double Amount, int apartmentid, int houseid) throws Exception{
        String query = "";
        if(apartmentid > 0) {
            query = "INSERT INTO `group6`.`bill` (`UserID`, `BillType`, `PaymentStatus`, `Amount`, `ApartmentID`) VALUES ("
                    + "\"" + UserID + "\", " + "\"" + BillType + "\", " + "\"" + 0 + "\", " + "\"" + Amount + "\", " + "\"" + apartmentid + "\")";
        }
        if(houseid > 0){
            query = "INSERT INTO `group6`.`bill` (`UserID`, `BillType`, `PaymentStatus`, `Amount`, `HouseID`) VALUES ("
                    + "\"" + UserID + "\", " + "\"" + BillType + "\", " + "\"" + 0 + "\", " + "\"" + Amount + "\", " + "\"" + houseid + "\")";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(query);
        st.close();
        con.close();
    }

    /*
     * Check user balance and history of balance based on user id
     * contained within the text file
     */
    public static ArrayList<String> ChHistory(int userid) throws Exception {
    	if (isOffline) {
    		ArrayList<String> offlineResult = new ArrayList<>();
			offlineResult.add("000001");offlineResult.add("$");offlineResult.add("500.00");
			offlineResult.add("000002");offlineResult.add("$");offlineResult.add("1200.00");
    		offlineResult.add("000003");offlineResult.add("$");offlineResult.add("800.00");
    		return offlineResult;
    	}
        String query = "select lease.LeaseID, lease.Status from lease where lease.MainUserID = " + "\"" + userid + "\"" + " ORDER BY lease.Status desc";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);

        ArrayList<String> myarray = new ArrayList<>();
        while (result.next() != false) {
            if(result.getString("Status").equals("1")) {
                myarray.add(result.getString("LeaseID"));
            }else{
                if(!myarray.contains("$")){
                    myarray.add("$");
                }
                if(result.getString("Status").equals("0")){
                    myarray.add(result.getString("LeaseID"));
                }
            }
        }
        st.close();
        con.close();
        return myarray;
    }

    public static ArrayList<String> CHLease(int leaseid) throws Exception{
        String query = "select * from lease where lease.LeaseID = " + "\"" + leaseid + "\"";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        ArrayList<String> myarray = new ArrayList<>();
        while (result.next() != false) {
            myarray.add(result.getString("LeaseID"));
            myarray.add(result.getString("LeaseOption"));
            myarray.add(result.getString("ApartmentID"));
            myarray.add(result.getString("HouseID"));
            myarray.add(result.getString("rentAmount"));
            myarray.add(result.getString("MainUserID"));
        }
        st.close();
        con.close();
        return myarray;
    }

    /*
     * Check the balance of a given user
     */
    public static Double ChBalance(int userid, int apartmentid, int houseid) throws Exception {
    	if (isOffline) {
    		return 1000.00;
    	}
        String query = "";
        if(apartmentid > 0) {
            query = "select bill.Amount from bill where bill.UserID = " + "\"" + userid + "\"" + " AND bill.PaymentStatus = 0 AND bill.ApartmentID = " + "\"" + apartmentid + "\"";
        }
        if(houseid > 0){
            query = "select bill.amount from bill where bill.UserID = " + "\"" + userid + "\"" + " AND bill.PaymentStatus = 0 AND bill.HouseID = " + "\"" + houseid + "\"";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);

        ArrayList<Double> myarray = new ArrayList<>();
        while (result.next() != false) {
            myarray.add(Double.parseDouble(result.getString("Amount")));
        }
        Double totalAmt = 0.0;
        try {
            for (int j = 0; !myarray.isEmpty(); j++) {

                totalAmt = totalAmt + myarray.get(j);
            }
        } catch (Exception e) {
        }
        st.close();
        con.close();
        return totalAmt;

    }

    /*
     * Add late fees/penalties to a user
     */
    public static void AddLFees(int userid, int apartmentid, int houseid) throws Exception {
    	if (isOffline) {
    		System.out.println("Added a late fee.");
    		return;
    	}
        String query = "";
        if(apartmentid > 0) {
            query = "select contract.LateRentPenalty from contract where contract.UserID = "
                    + "\"" + userid + "\"" + " AND contract.ApartmentID = " + "\"" + apartmentid + "\"";
        }
        if(houseid > 0){
            query = "select contract.LateRentPenalty from contract where contract.UserID = "
                    + "\"" + userid + "\"" + " AND contract.HouseID = " + "\"" + houseid + "\"";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);
        result.next();
        Double LateFee = Double.parseDouble(result.getString("LateRentPenalty"));
        System.out.println(LateFee);
        ArrayList<String> myarray = new ArrayList<>();
        st.close();
        con.close();
        CreateBill(userid,"Late Rent", LateFee, apartmentid, houseid);
    }

    /*
     * Generate reports about the revenue of the apartments
     * along with the number of tenants
     * Note: can also add any pending cancellations and late fees
     * that need to be added
     *
     */
    public static ArrayList<String> GenReport() throws Exception {
    	if (isOffline) {
    		ArrayList<String> offlineResult = new ArrayList<>();
    		offlineResult.add("000082"); offlineResult.add("Jacksonville"); offlineResult.add("10");
    		offlineResult.add("000324"); offlineResult.add("New York"); offlineResult.add("20");
    		offlineResult.add("001122"); offlineResult.add("Mars"); offlineResult.add("1337");
    		offlineResult.add("000021"); offlineResult.add("Area 11"); offlineResult.add("1738");
    		offlineResult.add("019990"); offlineResult.add("Shinjuku Ghetto"); offlineResult.add("420");
    		return offlineResult;
    	}
        String query = "select apartment.LocationID, location.LocationName, SUM(bill.Amount) AS Revenue from bill join apartment on apartment.ApartmentID = " +
                "bill.ApartmentID join location on apartment.LocationID = location.LocationID where bill.BillType = "+"\""+"rent"+"\""+" AND PaymentStatus = 1 GROUP BY LocationID";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        ResultSet result = st.executeQuery(query);

        ArrayList<String> myarray = new ArrayList<>();
        while (result.next() != false) {
            myarray.add(result.getString("LocationID"));
            myarray.add(result.getString("LocationName"));
            myarray.add(result.getString("Revenue"));
        }
        int i = 1;
        try {
            for (int j = 0; !myarray.isEmpty(); j++) {
                System.out.print(myarray.get(j) + " ");
                if(i%3 == 0){
                    System.out.println();
                }
                i++;
            }
        } catch (Exception e) {
        }
        st.close();
        con.close();
        return myarray;
    }
    public static void addApartment(ArrayList<String> apinfo)throws Exception{
    	if (isOffline) {
    		System.out.println("Apartment added.");
    		return;
    	}
        String query = "INSERT INTO `group6`.`apartment` (`ApartmentID`, `ApartmentNumber`, `BuildingID`, `Bedrooms`, `Bathrooms`, `Price`, `LocationID`) VALUES ("
        + "\"" + apinfo.get(0) + "\", " + "\"" + apinfo.get(1) + "\", " + "\"" + apinfo.get(2) + "\", " + "\"" + apinfo.get(3) + "\", " + "\"" + apinfo.get(4) + "\", "
                + "\"" + apinfo.get(5) + "\", " + "\"" + apinfo.get(6) + "\")";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(query);
        st.close();
        con.close();
    }
    public static void addHouse(ArrayList<String> hinfo) throws Exception{
    	if (isOffline) {
    		System.out.println("House added.");
    		return;
    	}
        String query = "INSERT INTO `group6`.`house` (`HouseID`, `Bedrooms`, `Bathrooms`, `Price`, `LocationID`) VALUES ("
        + "\"" + hinfo.get(0) + "\", " + "\"" + hinfo.get(1) + "\", " + "\"" + hinfo.get(2) + "\", " + "\"" + hinfo.get(3) + "\", " + "\"" + hinfo.get(4) + "\")";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(query);
        st.close();
        con.close();
    }

    public static void updateListing(int apartmentid, int houseid, Double Nprice) throws Exception{
    	if (isOffline) {
    		System.out.println("Listing updated.");
    		return;
    	}
        String query = "";
        if(apartmentid > 0){
            query = "UPDATE `group6`.`apartment` SET `Price` = " + "\"" + Nprice + "\"" + " WHERE (`ApartmentID` = " + "\"" + apartmentid +"\")";
        }
        if(houseid > 0){
            query = "UPDATE `group6`.`house` SET `Price` = " + "\"" + Nprice + "\"" + " WHERE (`HouseID` = " + "\"" + houseid +"\")";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(query);
        st.close();
        con.close();
    }

    public static void deleteListing(int apartmentid, int houseid) throws Exception{
    	if (isOffline) {
    		System.out.println("Listing deleted.");
    		return;
    	}
        String query = "";
        if(apartmentid > 0){
            query = "DELETE FROM `group6`.`apartment` WHERE (`ApartmentID` = " + "\"" + apartmentid + "\")";
        }
        if(houseid > 0){
            query = "DELETE FROM `group6`.`house` WHERE (`HouseID` = " + "\"" + houseid + "\")";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(URL, username, password);
        Statement st = con.createStatement();
        int count = st.executeUpdate(query);
        st.close();
        con.close();
    }
}
