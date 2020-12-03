import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.font.TextLayout;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Welcome to the Database UI
 * <p>
 * This class serves as a front-end application for managing an apartment/housing database.
 * It is best suited to be ran with a Database.java class as the back-end.
 * 
 * @author JazzyLucas
 */

class UserInterface extends JFrame implements ActionListener { 
	private static final long serialVersionUID = -1779981392814660970L; // Ignore this
	
	private static boolean isAdmin = false;
	public static boolean isLoggedIn = false;
	private static boolean hasSearched = false;
    static JFrame mainFrame; 
    static JPanel mainPanel;
    static JButton dashboardButton;
	
	// Login Elements
	static JPanel loginPanel;
	static String email;
	static String password;
    static JTextField emailtxtField;
    static JPasswordField passwordtxtField;
    static JLabel userMessage;
    static JButton signupButton;
    static JButton loginButton;
	static String signupAddress;
	static String signupPassword;
	static String signupFirstName;
	static String signupLastName;
	static String signupPhone;
	static String signupEmail;
	static String signupCity;
	static String signupState;
	static String signupZip;
    static JTextField signupFirstNametxtField;
    static JTextField signupLastNametxtField;
    static JTextField signupPhonetxtField;
    static JTextField signupEmailtxtField;
    static JTextField signupCitytxtField;
    static JTextField signupStatetxtField;
    static JTextField signupZiptxtField;
    static JTextField signupAddresstxtField;
    static JTextField signupPasswordtxtField;
    
    // Demo Login Elements
    static JCheckBox demoCheckBox;
    
    // Dashboard Elements
	static JPanel dashboardPanel;
	static JButton gotoSearchButton;
	static JButton gotoLeasesButton;
	static JButton gotoPaymentsButton;
	static JButton reviewButton;
	static JButton signOutButton;
	static JTextArea userInformation;
	// Admin stuff:
	static JButton createLeaseButton;
	static JButton checkUserButton;
	static JButton contractModificationButton;
	static JButton inventoryManagementButton;
	static JButton generateReportsButton;
    
    // Search Elements
    static JPanel searchPanel;
    static JTextField bedroomsField;
    static JTextField bathroomsField;
    static JTextField priceField;
    static JTextField locationField;
    static JRadioButton houseTypeRadio;
    static JRadioButton houseTypeRadio2;
    static JTextField amenitiesField;
    static JButton searchButton;
    static JPanel resultsPanel;
    static JTextArea apartmentInfo;
    static JScrollPane scroll;
    static JTextField apartmentIDField;
    static JButton checkApartmentButton;
    static JTextArea apartmentDetailstxtArea;
    static JPanel detailsPanel;
    static Boolean hasViewedDetails;
    static JScrollPane scroll2;
    static ArrayList<String> resultStrings;
    static ArrayList<String> detailStrings; 
	
	// Leases Elements
    static JPanel leasePanel;
    static JTextField applyForLeasetxtField;
    static JTextField roommatestxtField;
    static JTextField apartmentIDtxtField;
    static JTextField leaseOptiontxtField;
    static JButton applyForLeaseButton;
    static JPanel leaseInfoPanel;
    static ArrayList<String> leaseInfoStrings; 
    static ArrayList<JTextField> leaseInfos;
    
    // Payments Elements
    static JPanel paymentsPanel;
    static JPanel paymentsInfoPanel;
    static ArrayList<String> paymentsInfoStrings;
    static ArrayList<JTextField> paymentInfos;
    static JTextField paymentIDField;
    static JButton payButton;
    static JTextField billIDtxtField;
    static JTextField amounttxtField;
    static JButton payBillButton;
    
    // Review Elements
    static JPanel reviewPanel;
    static JTextField reviewIDField;
    static JTextField reviewScoreField;
    static JTextField reviewField;
    static JButton makeReviewButton;
    
    // CreateLease Elements
    static JPanel createLeasePanel;
    static JTextField createLeaseUserID;
    static JTextField createLeaseApartmentID;
    static JTextField createLeaseHouseID;
    static JTextField createLeaseRoomates;
    static JTextField createLeaseOptions;
    static JButton makeLeaseButton;
    
    // CheckUser Elements
    static JPanel checkUserPanel;
    static JTextField checkUserIDField;
    static JTextField checkLeaseIDField;
    static JTextArea informationArea;
    static JPanel informationPanel;
    static JButton checkUserButton_;
    static JButton leaseHistoryButton;
    static ArrayList<String> historyInformation;
    
    // Contract Modification Elements
    static JPanel contractModificationPanel;
    static JTextField contractUserIDField;
    static JTextField contractApartmentIDField;
    static JTextField contractHouseIDField;
    static JButton earlyTerminationButton;
    static JButton terminateContractButton;
    static JButton lateContractButton;
    
    // ManageInventory Elements
    static JPanel manageInventoryPanel;
    static JTextField miHouseIDField;
    static JTextField miBedroomsField;
    static JTextField miBathroomsField;
    static JTextField miPriceField;
    static JTextField miLocationField;
    static JTextField miApartmentIDField;
	static JTextField miApartmentNumberField;
	static JTextField miBuildingIDField;
	static JButton addHouseButton;
	static JButton deleteInventoryButton;
	static JButton updateInventoryButton;
	static JButton addApartmentButton;
    
    // GenerateReports Elements
    static JPanel generateReportsPanel;
    static JTextArea reportsTextArea;
    static ArrayList<String> reportInfo;
    static JButton applicationsButton;
    static JTextArea applicationsTextArea;
    
    // Apartment Elements (for getting details on a specific apartment)
    static JPanel apartmentPanel;
    static JLabel numberBedrooms;
    static JLabel numberBathrooms;
    static JLabel price;
    static JLabel address;
    static JLabel amenities;
    static JLabel leaseOptions;
    static JLabel characteristics;
    
    static UserInterface UI;
  
    // main class 
    public static void main(String[] args){ 
    	/*
    	 * Initiations
    	 */
        UI = new UserInterface();
        mainFrame = new JFrame("DB Project");
        mainPanel = new JPanel();
        setupLoginPanel();
        userInformation = new JTextArea();
        userInformation.setText("[User Information]");
        userInformation.setEditable(false);
        mainFrame.add(mainPanel);
        mainFrame.setSize(900, 900); 
        mainFrame.setVisible(true);
    	
        displayLogin();
    }
    
    public static void displayLogin() {
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
        mainPanel.add(loginPanel); 
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    public static void displayDashboard() {
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
    	mainPanel.add(dashboardPanel);
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    public static void displaySearch() {
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
    	mainPanel.add(searchPanel);
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    public static void displayLeases() {
    	updateLeasePanel();
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
    	mainPanel.add(leasePanel);
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    public static void displayPayments() {
    	updatePaymentsPanel();
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
    	mainPanel.add(paymentsPanel);
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    public static void displayReview() {
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
    	mainPanel.add(reviewPanel);
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    public static void displayCreateLease() {
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
    	mainPanel.add(createLeasePanel);
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    public static void displayCheckUser() {
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
    	mainPanel.add(checkUserPanel);
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    public static void displayModifyContractsPanel() {
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
    	mainPanel.add(contractModificationPanel);
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    public static void displayManageInventory() {
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
    	mainPanel.add(manageInventoryPanel);
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    public static void displayGenerateReports() {
    	for (Component c : mainPanel.getComponents()) {
    		mainPanel.remove(c);
    	}
    	mainPanel.add(generateReportsPanel);
    	mainPanel.revalidate();
    	mainPanel.repaint();
    }
    
    private static void setupLoginPanel() {
    	// Set up overall panel
        loginPanel = new JPanel();
        BoxLayout layout = new BoxLayout(loginPanel, BoxLayout.Y_AXIS);
    	loginPanel.setLayout(layout);
    	JPanel panel1 = new JPanel();
    	JPanel panel2 = new JPanel();
    	panel1.setLayout(new GridLayout(2,2));
    	panel2.setLayout(new GridLayout(9,2));
    	// Set up login panel
        JLabel emailLabel = new JLabel("Email: "); 
        JLabel passwordLabel = new JLabel("Password: ");
        emailtxtField = new JTextField("", 16);
        passwordtxtField = new JPasswordField("", 16);
        emailLabel.setLabelFor(emailtxtField);
        passwordLabel.setLabelFor(passwordtxtField);
    	JLabel loginTitle = new JLabel("Welcome to the Apartment Database");
    	loginTitle.setHorizontalAlignment(JLabel.CENTER);
        userMessage = new JLabel("");
    	userMessage.setHorizontalAlignment(JLabel.CENTER);
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        loginButton.addActionListener(UI); 
        signupButton.addActionListener(UI);
        panel1.add(emailLabel);
        panel1.add(emailtxtField);
        panel1.add(passwordLabel);
        panel1.add(passwordtxtField);
        // Set up signup panel
        JLabel signupFirstNameLabel = new JLabel("First Name: "); 
        JLabel signupLastNameLabel = new JLabel("Last Name: ");
        JLabel signupPhoneLabel = new JLabel("Phone: "); 
        JLabel signupEmailLabel = new JLabel("Email: ");
        JLabel signupCityLabel = new JLabel("City: "); 
        JLabel signupStateLabel = new JLabel("State: ");
        JLabel signupZipLabel = new JLabel("Zip: "); 
        JLabel signupAddressLabel = new JLabel("Address: "); 
        JLabel signupPasswordLabel = new JLabel("Password: ");
        signupAddresstxtField = new JTextField("", 16);
        signupPasswordtxtField = new JTextField("", 16);
        signupFirstNametxtField = new JTextField("", 20);
        signupLastNametxtField = new JTextField("", 20);
        signupPhonetxtField = new JTextField("", 10);
        signupEmailtxtField = new JTextField("", 20);
        signupCitytxtField = new JTextField("", 20);
        signupStatetxtField = new JTextField("", 20);
        signupZiptxtField = new JTextField("", 5);
        signupAddressLabel.setLabelFor(signupAddresstxtField);
        signupPasswordLabel.setLabelFor(signupPasswordtxtField);
        signupFirstNameLabel.setLabelFor(signupFirstNametxtField);
        signupLastNameLabel.setLabelFor(signupLastNametxtField);
        signupPhoneLabel.setLabelFor(signupPhonetxtField);
        signupEmailLabel.setLabelFor(signupEmailtxtField);
        signupCityLabel.setLabelFor(signupCitytxtField);
        signupStateLabel.setLabelFor(signupStatetxtField);
        signupZipLabel.setLabelFor(signupZiptxtField);
    	JLabel signupTitle = new JLabel("Signup:");
    	signupTitle.setHorizontalAlignment(JLabel.CENTER);
    	panel2.add(signupFirstNameLabel);
    	panel2.add(signupFirstNametxtField);
    	panel2.add(signupLastNameLabel);
    	panel2.add(signupLastNametxtField);
    	panel2.add(signupPhoneLabel);
    	panel2.add(signupPhonetxtField);
    	panel2.add(signupEmailLabel);
    	panel2.add(signupEmailtxtField);
    	panel2.add(signupCityLabel);
    	panel2.add(signupCitytxtField);
    	panel2.add(signupStateLabel);
    	panel2.add(signupStatetxtField);
    	panel2.add(signupZipLabel);
    	panel2.add(signupZiptxtField);
    	panel2.add(signupAddressLabel);
    	panel2.add(signupAddresstxtField);
    	panel2.add(signupPasswordLabel);
    	panel2.add(signupPasswordtxtField);
    	// Demo stuff
    	JPanel panel3 = new JPanel();
    	panel3.setLayout(new GridLayout(1,2));
    	demoCheckBox = new JCheckBox();
    	JLabel demoLabel = new JLabel("Offline Mode? ");
    	panel3.add(demoLabel);
    	panel3.add(demoCheckBox);
    	// Put it all together
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(loginTitle);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(panel1);
        loginPanel.add(userMessage);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(loginButton);
        loginPanel.add(Box.createVerticalStrut(10));
    	loginPanel.add(signupTitle);
        loginPanel.add(Box.createVerticalStrut(10));
    	loginPanel.add(panel2);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(signupButton);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(panel3);
    }
    
    private static void setupPanels() {
    	isLoggedIn = true;
        GridLayout layout = new GridLayout(20,1); // 20 rows, 1 column
        dashboardButton = new JButton("Dashboard");
    	dashboardButton.addActionListener(UI);
    	dashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Dashboard
        dashboardPanel = new JPanel();
        dashboardPanel.setLayout(layout);
        JPanel userPanel = new JPanel();
        JLabel dashboardTitle = new JLabel("Dashboard");
        dashboardTitle.setHorizontalAlignment(JLabel.CENTER);
        gotoSearchButton = new JButton("Search Page");
        gotoLeasesButton = new JButton("Leases");
        gotoPaymentsButton = new JButton("Payments");
        reviewButton = new JButton("Review");
        signOutButton = new JButton("Sign Out");
        dashboardPanel.add(dashboardTitle);
    	userPanel.add(gotoSearchButton);
    	userPanel.add(gotoLeasesButton);
		userPanel.add(gotoPaymentsButton);
		userPanel.add(reviewButton);
		dashboardPanel.add(userPanel);
    	if (isAdmin) {
            JPanel adminPanel = new JPanel();
            createLeaseButton = new JButton("Create Lease");
            checkUserButton = new JButton("Check User");
            contractModificationButton = new JButton("Modify Contracts");
            inventoryManagementButton = new JButton("Manage Inventory");
            generateReportsButton = new JButton("Generate Reports");
    		adminPanel.add(createLeaseButton);
			adminPanel.add(checkUserButton);
			adminPanel.add(contractModificationButton);
			adminPanel.add(inventoryManagementButton);
			adminPanel.add(generateReportsButton);
			dashboardPanel.add(adminPanel);
    	}
    	gotoSearchButton.addActionListener(UI);
    	gotoLeasesButton.addActionListener(UI);
    	gotoPaymentsButton.addActionListener(UI);
    	reviewButton.addActionListener(UI);
    	if (isAdmin) {
    		contractModificationButton.addActionListener(UI);
	    	createLeaseButton.addActionListener(UI);
	    	checkUserButton.addActionListener(UI);
    		inventoryManagementButton.addActionListener(UI);
	    	generateReportsButton.addActionListener(UI);
    	}   
    	signOutButton.addActionListener(UI);
        dashboardPanel.add(Box.createVerticalStrut(5));
        dashboardPanel.add(userInformation);
        dashboardPanel.add(Box.createVerticalStrut(10));
    	dashboardPanel.add(signOutButton);
    	
    	// Other panels
    	setupSearchPanel();
        setupLeasePanel();
        setupPaymentsPanel();
        setupReviewPanel();
        if (isAdmin) {
        	setupCreateLeasePanel();
        	setupCheckUserPanel();
        	setupContractModificationPanel();
        	setupInventoryPanel();
        	setupGenerateReportsPanel();
        }
    }
    
    private static void setupLeasePanel() {
    	leasePanel = new JPanel();
        BoxLayout leaseLayout = new BoxLayout(leasePanel, BoxLayout.Y_AXIS);
        leasePanel.setLayout(leaseLayout);
    	leaseInfos = new ArrayList<JTextField>();
    	JLabel leaseTitle = new JLabel("Leases");
    	JLabel applyForLeaseTitle = new JLabel("Fill out info to apply for lease: ");
    	roommatestxtField = new JTextField("Roommates");
    	apartmentIDtxtField = new JTextField("Apartment/House ID");
    	leaseOptiontxtField = new JTextField("Lease Option");
    	applyForLeaseButton = new JButton("Apply");
    	applyForLeaseButton.addActionListener(UI);
    	leaseTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        leasePanel.add(Box.createVerticalStrut(10));
    	leasePanel.add(leaseTitle);
        leasePanel.add(Box.createVerticalStrut(10));
        leasePanel.add(dashboardButton);
        leasePanel.add(Box.createVerticalStrut(10));
        leasePanel.add(applyForLeaseTitle);
        leasePanel.add(Box.createVerticalStrut(5));
        leasePanel.add(roommatestxtField);
        leasePanel.add(Box.createVerticalStrut(5));
        leasePanel.add(apartmentIDtxtField);
        leasePanel.add(Box.createVerticalStrut(5));
        leasePanel.add(leaseOptiontxtField);
        leasePanel.add(Box.createVerticalStrut(5));
        leasePanel.add(applyForLeaseButton);
        leasePanel.add(Box.createVerticalStrut(10));
        leasePanel.add(new JLabel("Your Leases: "));
		leaseInfoPanel = new JPanel();
        BoxLayout infoLayout = new BoxLayout(leaseInfoPanel, BoxLayout.Y_AXIS);
        leaseInfoPanel.setLayout(infoLayout);
    }
    
    private static void setupSearchPanel() {
        GridLayout layout2 = new GridLayout(6,2);
        searchPanel = new JPanel();
        BoxLayout searchLayout = new BoxLayout(searchPanel, BoxLayout.Y_AXIS);
        searchPanel.setLayout(searchLayout);
        JLabel searchTitle = new JLabel("Search");
        searchTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(searchTitle);
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(Box.createVerticalStrut(10));
        JButton tempDashboardButton = new JButton("Dashboard");
        tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(tempDashboardButton);
        searchPanel.add(Box.createVerticalStrut(10));
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(layout2);
        JLabel bedroomsLabel = new JLabel("Bedrooms");
        JLabel bathroomsLabel = new JLabel("Bathrooms");
        JLabel priceLabel = new JLabel("Price");
        JLabel houseTypeLabel = new JLabel("Type:");
        JLabel houseTypeLabel2 = new JLabel(" ");
        JLabel amenitiesLabel = new JLabel("Amenities");
        optionsPanel.add(bedroomsLabel);
        bedroomsField = new JTextField("0", 2);
        optionsPanel.add(bedroomsField);
        optionsPanel.add(bathroomsLabel);
        bathroomsField = new JTextField("0", 2);
        optionsPanel.add(bathroomsField);
        optionsPanel.add(priceLabel);
        priceField = new JTextField("0.0", 16);
        optionsPanel.add(priceField);
        ButtonGroup radioButtons = new ButtonGroup();
        houseTypeRadio = new JRadioButton("House");
        houseTypeRadio.setSelected(true);
        houseTypeRadio2 = new JRadioButton("Apartment");
        radioButtons.add(houseTypeRadio);
        radioButtons.add(houseTypeRadio2);
        optionsPanel.add(houseTypeLabel);
        optionsPanel.add(houseTypeRadio);
        optionsPanel.add(houseTypeLabel2);
        optionsPanel.add(houseTypeRadio2);
        optionsPanel.add(amenitiesLabel);
        amenitiesField = new JTextField("", 16);
        optionsPanel.add(amenitiesField);
        searchPanel.add(optionsPanel);
        searchButton = new JButton("Search");
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setSize(10, 10);
        searchButton.addActionListener(UI);
        searchPanel.add(Box.createVerticalGlue());
        searchPanel.add(Box.createVerticalStrut(10));
        searchPanel.add(searchButton);
        searchPanel.add(Box.createVerticalStrut(10));
    }
    
    private static void setupPaymentsPanel() {
        paymentsPanel = new JPanel();
        paymentsInfoPanel = new JPanel();
        BoxLayout paymentsLayout = new BoxLayout(paymentsPanel, BoxLayout.Y_AXIS);
        paymentsPanel.setLayout(paymentsLayout);
        JLabel paymentsTitle = new JLabel("Payments");
        paymentsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        billIDtxtField = new JTextField("Bill ID");
        billIDtxtField.setAlignmentX(Component.CENTER_ALIGNMENT);
        amounttxtField = new JTextField("00.00");
        amounttxtField.setAlignmentX(Component.CENTER_ALIGNMENT);
        payBillButton = new JButton("Pay Bill");
        payBillButton.addActionListener(UI);
        payBillButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        paymentsPanel.add(Box.createVerticalStrut(10));
        paymentsPanel.add(paymentsTitle);
        paymentsPanel.add(Box.createVerticalStrut(10));
        JButton tempDashboardButton = new JButton("Dashboard");
        tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        paymentsPanel.add(tempDashboardButton);
        paymentsPanel.add(Box.createVerticalStrut(10));
        paymentsPanel.add(billIDtxtField);
        //paymentsPanel.add(Box.createVerticalStrut(5)); Commented out because Database.java doesn't require a payment amount
        //paymentsPanel.add(amounttxtField);
        paymentsPanel.add(Box.createVerticalStrut(5));
        paymentsPanel.add(payBillButton);
        paymentsPanel.add(Box.createVerticalStrut(10));
    	paymentsInfoStrings = new ArrayList<String>();
    	paymentInfos = new ArrayList<JTextField>();
    	paymentIDField = new JTextField();
    	payButton = new JButton();
    	payButton.addActionListener(UI);
    	payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    
    private static void setupReviewPanel() {
    	GridLayout layout2 = new GridLayout(6,2);
        reviewPanel = new JPanel();
        BoxLayout reviewLayout = new BoxLayout(reviewPanel, BoxLayout.Y_AXIS);
        reviewPanel.setLayout(reviewLayout);
        JLabel reviewTitle = new JLabel("Review");
        reviewTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewPanel.add(Box.createVerticalStrut(10));
        reviewPanel.add(reviewTitle);
        reviewPanel.add(Box.createVerticalStrut(10));
        JButton tempDashboardButton = new JButton("Dashboard");
        tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewPanel.add(tempDashboardButton);
        reviewPanel.add(Box.createVerticalStrut(10));
        JPanel reviewStuffPanel = new JPanel();
        reviewStuffPanel.setLayout(layout2);
        JLabel reviewIDLabel = new JLabel("House/Apartment ID");
        JLabel reviewScoreLabel = new JLabel("Score (out of 5)");
        JLabel reviewFieldLabel = new JLabel("Review (optional)");
        reviewIDField = new JTextField("");
        reviewScoreField = new JTextField("", 1);
        reviewField = new JTextField("", 40);
        reviewStuffPanel.add(reviewIDLabel);
        reviewStuffPanel.add(reviewIDField);
        reviewStuffPanel.add(reviewScoreLabel);
        reviewStuffPanel.add(reviewScoreField);
        reviewStuffPanel.add(reviewFieldLabel);
        reviewStuffPanel.add(reviewField);
        reviewPanel.add(reviewStuffPanel);
        makeReviewButton = new JButton("Make review");
        makeReviewButton.addActionListener(UI);
        makeReviewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewPanel.add(Box.createVerticalStrut(10));
        reviewPanel.add(makeReviewButton);
    }
    
    private static void setupCreateLeasePanel() {
        createLeasePanel = new JPanel();
    	BoxLayout createLeaseLayout = new BoxLayout(createLeasePanel, BoxLayout.Y_AXIS);
    	createLeasePanel.setLayout(createLeaseLayout);
    	JLabel createLeaseTitle = new JLabel("Create Lease");
    	createLeaseTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(5,2));
        JLabel createLeaseUserIDLabel = new JLabel("User ID");
        createLeaseUserID = new JTextField("");
        JLabel createLeaseApartmentIDLabel = new JLabel("Apartment ID");
        createLeaseApartmentID = new JTextField("");
        JLabel createLeaseHouseIDLabel = new JLabel("House ID");
        createLeaseHouseID = new JTextField("");
        JLabel createLeaseRoomatesLabel = new JLabel("Roommates");
        createLeaseRoomates = new JTextField("");
        JLabel createLeaseOptionsLabel = new JLabel("Lease Options");
        createLeaseOptions = new JTextField("");
        panel1.add(createLeaseUserIDLabel);
        panel1.add(createLeaseUserID);
        panel1.add(createLeaseApartmentIDLabel);
        panel1.add(createLeaseApartmentID);
        panel1.add(createLeaseHouseIDLabel);
        panel1.add(createLeaseHouseID);
        panel1.add(createLeaseRoomatesLabel);
        panel1.add(createLeaseRoomates);
        panel1.add(createLeaseOptionsLabel);
        panel1.add(createLeaseOptions);
        makeLeaseButton = new JButton("Make Lease");
        makeLeaseButton.addActionListener(UI);
        makeLeaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton tempDashboardButton = new JButton("Dashboard");
        tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createLeasePanel.add(createLeaseTitle);
        createLeasePanel.add(Box.createVerticalStrut(10));
        createLeasePanel.add(panel1);
        createLeasePanel.add(Box.createVerticalStrut(10));
        createLeasePanel.add(makeLeaseButton);
        createLeasePanel.add(Box.createVerticalStrut(10));
        createLeasePanel.add(tempDashboardButton);
    }
    
    private static void setupCheckUserPanel() {
    	checkUserPanel = new JPanel();
    	checkUserPanel.setLayout(new BoxLayout(checkUserPanel, BoxLayout.Y_AXIS));
    	JPanel fieldsPanel = new JPanel();
    	fieldsPanel.setLayout(new GridLayout(2,2));
    	JLabel checkUserTitle = new JLabel("Check a User or Lease");
    	checkUserTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    	JLabel userIDLabel = new JLabel("User ID: ");
    	JLabel leaseIDLabel = new JLabel("Lease ID: ");
    	checkUserIDField = new JTextField("");
    	checkLeaseIDField = new JTextField("");
    	informationArea = new JTextArea(10, 20);
    	informationArea.setText("User info will be here.");
    	informationPanel = new JPanel();
    	fieldsPanel.add(userIDLabel);
    	fieldsPanel.add(checkUserIDField);
    	fieldsPanel.add(leaseIDLabel);
    	fieldsPanel.add(checkLeaseIDField);
    	checkUserButton_ = new JButton("Check The User");
    	checkUserButton_.addActionListener(UI);
    	checkUserButton_.setAlignmentX(Component.CENTER_ALIGNMENT);
    	leaseHistoryButton = new JButton("Check History");
    	leaseHistoryButton.addActionListener(UI);
    	leaseHistoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	checkUserPanel.add(checkUserTitle);
    	checkUserPanel.add(Box.createVerticalStrut(10));
    	checkUserPanel.add(fieldsPanel);
    	checkUserPanel.add(Box.createVerticalStrut(10));
    	checkUserPanel.add(checkUserButton_);
    	checkUserPanel.add(Box.createVerticalStrut(10));
    	checkUserPanel.add(leaseHistoryButton);
        JButton tempDashboardButton = new JButton("Dashboard");
    	tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	checkUserPanel.add(Box.createVerticalStrut(10));
        checkUserPanel.add(informationArea);
    	checkUserPanel.add(Box.createVerticalStrut(10));
        checkUserPanel.add(tempDashboardButton);
    }
    
    private static void setupContractModificationPanel() {
    	contractModificationPanel = new JPanel();
    	contractModificationPanel.setLayout(new BoxLayout(contractModificationPanel, BoxLayout.Y_AXIS));
    	JPanel fieldsPanel = new JPanel();
    	fieldsPanel.setLayout(new GridLayout(3,2));
    	JLabel contractModificationTitle = new JLabel("Modify Contracts");
    	contractModificationTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    	JLabel userIDLabel = new JLabel("User ID: ");
    	JLabel apartmentIDLabel = new JLabel("Apartment ID: ");
    	JLabel houseIDLabel = new JLabel("House ID: ");
    	contractUserIDField = new JTextField("");
    	contractApartmentIDField = new JTextField("");
    	contractHouseIDField = new JTextField("");
    	fieldsPanel.add(userIDLabel);
    	fieldsPanel.add(contractUserIDField);
    	fieldsPanel.add(apartmentIDLabel);
    	fieldsPanel.add(contractApartmentIDField);
    	fieldsPanel.add(houseIDLabel);
    	fieldsPanel.add(contractHouseIDField);
    	earlyTerminationButton = new JButton("Early Termination");
    	earlyTerminationButton.addActionListener(UI);
    	earlyTerminationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	terminateContractButton = new JButton("Terminate Contract");
    	terminateContractButton.addActionListener(UI);
    	terminateContractButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	lateContractButton = new JButton("Add Late Fee");
    	lateContractButton.addActionListener(UI);
    	lateContractButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	contractModificationPanel.add(contractModificationTitle);
    	contractModificationPanel.add(Box.createVerticalStrut(10));
    	contractModificationPanel.add(fieldsPanel);
    	contractModificationPanel.add(Box.createVerticalStrut(10));
    	contractModificationPanel.add(earlyTerminationButton);
    	contractModificationPanel.add(Box.createVerticalStrut(10));
    	contractModificationPanel.add(terminateContractButton);
    	contractModificationPanel.add(Box.createVerticalStrut(10));
    	contractModificationPanel.add(lateContractButton);
        JButton tempDashboardButton = new JButton("Dashboard");
    	tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        contractModificationPanel.add(Box.createVerticalStrut(10));
        contractModificationPanel.add(tempDashboardButton);
    }
    
    private static void setupInventoryPanel() {
    	manageInventoryPanel = new JPanel();
    	manageInventoryPanel.setLayout(new BoxLayout(manageInventoryPanel, BoxLayout.Y_AXIS));
    	JLabel inventoryTitle = new JLabel("Modify Inventory");
    	inventoryTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    	manageInventoryPanel.add(inventoryTitle);
        JButton tempDashboardButton = new JButton("Dashboard");
    	tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addHouseButton = new JButton("Add House");
        addApartmentButton = new JButton("Add Apartment");
        deleteInventoryButton = new JButton("Delete Inventory");
        updateInventoryButton = new JButton("Update Inventory");
    	addHouseButton.addActionListener(UI);
    	addApartmentButton.addActionListener(UI);
    	deleteInventoryButton.addActionListener(UI);
    	updateInventoryButton.addActionListener(UI);
    	miHouseIDField = new JTextField("");
    	miBedroomsField = new JTextField("0");
    	miBathroomsField = new JTextField("0");
    	miPriceField = new JTextField("0.0");
    	miLocationField = new JTextField("");
    	miApartmentIDField = new JTextField("");
    	miApartmentNumberField = new JTextField("");
    	miBuildingIDField = new JTextField("");
    	addHouseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	addApartmentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	deleteInventoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	updateInventoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    	manageInventoryPanel.add(tempDashboardButton);
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    	manageInventoryPanel.add(new JLabel("Apartment ID"));
    	manageInventoryPanel.add(miApartmentIDField);
    	manageInventoryPanel.add(Box.createVerticalStrut(5));
    	manageInventoryPanel.add(new JLabel("Apartment Number"));
    	manageInventoryPanel.add(miApartmentNumberField);
    	manageInventoryPanel.add(Box.createVerticalStrut(5));
    	manageInventoryPanel.add(new JLabel("Building ID"));
    	manageInventoryPanel.add(miBuildingIDField);
    	manageInventoryPanel.add(Box.createVerticalStrut(5));
    	manageInventoryPanel.add(new JLabel("House ID"));
    	manageInventoryPanel.add(miHouseIDField);
    	manageInventoryPanel.add(Box.createVerticalStrut(5));
    	manageInventoryPanel.add(new JLabel("Bedrooms"));
    	manageInventoryPanel.add(miBedroomsField);
    	manageInventoryPanel.add(Box.createVerticalStrut(5));
    	manageInventoryPanel.add(new JLabel("Bathrooms"));
    	manageInventoryPanel.add(miBathroomsField);
    	manageInventoryPanel.add(Box.createVerticalStrut(5));
    	manageInventoryPanel.add(new JLabel("Price"));
    	manageInventoryPanel.add(miPriceField);
    	manageInventoryPanel.add(Box.createVerticalStrut(5));
    	manageInventoryPanel.add(new JLabel("Location"));
    	manageInventoryPanel.add(miLocationField);
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    	manageInventoryPanel.add(addHouseButton);
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    	manageInventoryPanel.add(addApartmentButton);
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    	manageInventoryPanel.add(deleteInventoryButton);
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    	manageInventoryPanel.add(updateInventoryButton);
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    }
    
    private static void resetmiFields() {
    	miApartmentNumberField.setText("");
    	miBuildingIDField.setText("");
    	miHouseIDField.setText("");
    	miBedroomsField.setText("");
    	miBathroomsField.setText("");
    	miPriceField.setText("");
    	miLocationField.setText("");
    	miApartmentIDField.setText("");
    }
    
    private static void setupGenerateReportsPanel() {
    	generateReportsPanel = new JPanel();
    	generateReportsPanel.setLayout(new BoxLayout(generateReportsPanel, BoxLayout.Y_AXIS));
    	JLabel title = new JLabel("Generated Report:");
    	title.setAlignmentX(Component.CENTER_ALIGNMENT);
    	generateReportsPanel.add(title);
    	generateReportsPanel.add(Box.createVerticalStrut(10));
        JButton tempDashboardButton = new JButton("Dashboard");
    	tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        generateReportsPanel.add(tempDashboardButton);
    	reportsTextArea = new JTextArea(15,30);
    	reportsTextArea.setEditable(false);
    	generateReportsPanel.add(Box.createVerticalStrut(10));
        JScrollPane scroll3 = new JScrollPane(reportsTextArea);
        scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        generateReportsPanel.add(scroll3);
        applicationsButton = new JButton("View Applications");
        applicationsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        applicationsButton.addActionListener(UI);
    	applicationsTextArea = new JTextArea(15,30);
    	applicationsTextArea.setEditable(false);
    	generateReportsPanel.add(Box.createVerticalStrut(10));
        generateReportsPanel.add(applicationsButton);
    	generateReportsPanel.add(Box.createVerticalStrut(10));
        JScrollPane scroll4 = new JScrollPane(applicationsTextArea);
        scroll4.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        generateReportsPanel.add(scroll4);
    }
    
    private static void updateGenerateReportsPanel() {
    	try {
			reportInfo = Database.GenReport();
			reportsTextArea.setText("");
			reportsTextArea.append("Generated Report:");
			int i = 0;
			for (String string : reportInfo) {
				switch (i) {
				case 0:
					reportsTextArea.append("\n\nLocation ID: " + string);
					break;
				case 1:
					reportsTextArea.append("\nLocation Name: " + string);
					break;
				case 2:
					reportsTextArea.append("\nRevenue: " + string);
					i = -1;
					break;
				default:
					break;
				}
				i++;
			}
		} catch (Exception e) {
			System.out.println("Unable to print a report.");
		}
    	generateReportsPanel.revalidate();
    	generateReportsPanel.repaint();
    }
    
    private static void updateViewApplications() {
    	try {
			ArrayList<String> newList = Database.viewAllApplications();
			applicationsTextArea.setText("");
			applicationsTextArea.append("Applications:");
			int i = 0;
			for (String string : newList) {
				switch (i) {
				case 0:
					applicationsTextArea.append("\n\nApplication ID: " + string);
					break;
				case 1:
					applicationsTextArea.append("\nMain User ID: " + string);
					break;
				case 2:
					applicationsTextArea.append("\nIncome: " + string);
					break;
				case 3:
					applicationsTextArea.append("\nApartment ID: " + string);
					break;
				case 4:
					applicationsTextArea.append("\nHouse ID: " + string);
					break;
				case 5:
					applicationsTextArea.append("\nApplication Status: " + string);
					break;
				case 6:
					applicationsTextArea.append("\nLease Options: " + string);
					i = -1;
					break;
				default:
					break;
				}
				i++;
			}
		} catch (Exception e) {
			System.out.println("Unable to view applications.");
		}
    	generateReportsPanel.revalidate();
    	generateReportsPanel.repaint();
    }
    
    private static void updateCheckUserPanel() {
    	checkUserPanel.revalidate();
    	checkUserPanel.repaint();
    }
    
    private static void updatePaymentsPanel() {
    	JTextField defaultResult = new JTextField("No bills yet.");
    	defaultResult.setEditable(false);
    	for (Component c : paymentsInfoPanel.getComponents()) {
    		paymentsInfoPanel.remove(c);
    	}
    	if (paymentInfos.isEmpty()) {
            paymentsInfoPanel.add(Box.createVerticalStrut(10));
    		paymentsInfoPanel.add(defaultResult);
            paymentsPanel.add(paymentsInfoPanel);
        	paymentsPanel.revalidate();
        	paymentsPanel.repaint();
    		return;
    	}
    	int i = 1;
    	try {
			for (JTextField textField : paymentInfos) {
			    paymentsInfoPanel.add(Box.createVerticalStrut(10));
				paymentsInfoPanel.add(new JLabel("Payment " + i));
				paymentsInfoPanel.add(textField);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	paymentsPanel.revalidate();
    	paymentsPanel.repaint();
    }
    
    private static void updateLeasePanel() {
    	for (Component c : leaseInfoPanel.getComponents()) {
    		leaseInfoPanel.remove(c);
    	}
    	if (leaseInfos.isEmpty()) {
            leaseInfoPanel.add(Box.createVerticalStrut(10));
    		leaseInfoPanel.add(new JTextField("No leases yet."));
            leasePanel.add(leaseInfoPanel);
        	leaseInfoPanel.revalidate();
        	leaseInfoPanel.repaint();
    		return;
    	}
    	int i = 1;
    	for (JTextField textField : leaseInfos) {
            leaseInfoPanel.add(Box.createVerticalStrut(10));
    		leaseInfoPanel.add(new JLabel("Lease " + i));
    		leaseInfoPanel.add(textField);
    	}
    	leasePanel.revalidate();
    	leasePanel.repaint();
    }
    
    private static void displaySearchResults() {
        apartmentInfo = new JTextArea(15,30);
        apartmentInfo.append("Results: ");
    	for (String string : resultStrings) {
            int PIDInt = Integer.parseInt(string);
            ArrayList<String> tempList = new ArrayList<String>();
            try {
                tempList = Database.propertyDetails(PIDInt, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int i = 0;
            for (String displayThing : tempList) {
                i++;
                if (displayThing == null)
                    displayThing = "";
                switch (i) {
                case 1:
                    apartmentInfo.append("\nApartmentID: " + displayThing);
                    break;
                case 2:
                    apartmentInfo.append("\nLocation: " + displayThing);
                    break;
                case 3:
                    apartmentInfo.append("\nApartment Number: " + displayThing);
                    break;
                case 4:
                    apartmentInfo.append("\nBuildingID: " + displayThing);
                    break;
                case 5:
                    apartmentInfo.append("\nBedrooms: " + displayThing);
                    break;
                case 6:
                    apartmentInfo.append("\nBathrooms: " + displayThing);
                    break;
                case 7:
                    apartmentInfo.append("\nPrice: " + displayThing);
                    break;
                case 8:
                    apartmentInfo.append("\nLease Options: " + displayThing + "\n");
                    i = 0;
                    break;
                }
            }
        }
        if (!hasSearched) {
            resultsPanel = new JPanel();
            apartmentInfo.setEditable(false);
            searchPanel.add(resultsPanel);
            apartmentIDField = new JTextField("ID");
            searchPanel.add(Box.createVerticalStrut(10));
            searchPanel.add(apartmentIDField);
            searchPanel.add(Box.createVerticalStrut(10));
            checkApartmentButton = new JButton("Details");
            checkApartmentButton.addActionListener(UI);
            checkApartmentButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            searchPanel.add(checkApartmentButton);
            hasViewedDetails = false;
        }
        for (Component c : resultsPanel.getComponents()) {
            resultsPanel.remove(c);
        }
        scroll = new JScrollPane(apartmentInfo);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resultsPanel.add(scroll);
        //resultsPanel.add(apartmentInfo);
        resultsPanel.add(scroll);
        resultsPanel.revalidate();
        resultsPanel.repaint();
        searchPanel.revalidate();
        searchPanel.repaint();
        mainPanel.revalidate();
        mainPanel.repaint();
        hasSearched = true;
    }
      
    private static void displayApartmentInfo() {
        apartmentDetailstxtArea = new JTextArea(15,30);
        apartmentDetailstxtArea.append("Information: ");
    	for (String string : detailStrings) {
            apartmentDetailstxtArea.append("\n" + string);
        }
        if (!hasViewedDetails) {
            detailsPanel = new JPanel();
            apartmentDetailstxtArea.setEditable(false);
            searchPanel.add(detailsPanel);
        }
        for (Component c : detailsPanel.getComponents()) {
        	detailsPanel.remove(c);
        }
        scroll2 = new JScrollPane(apartmentDetailstxtArea);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        detailsPanel.add(scroll2);
        detailsPanel.revalidate();
        detailsPanel.repaint();
        searchPanel.revalidate();
        searchPanel.repaint();
        mainPanel.revalidate();
        mainPanel.repaint();
        hasViewedDetails = true;
    }
    
    public void actionPerformed(ActionEvent e) 
    { 
        String s = e.getActionCommand(); 
        
        /*
         * Dashboard buttons
         */
        
        if (s.equals("Sign Out")) {
        	displayLogin();
        	isLoggedIn = false;
            userMessage.setText("Signed out.");
        }
        
        if (s.equals("Dashboard")) {
        	displayDashboard();
        }
        
        if (s.equals("Search Page")) {
        	displaySearch();
        }
        
        if (s.equals("Leases")) {
        	displayLeases();
        }
        
        if (s.equals("Payments")) {
        	displayPayments();
        }
        
        if (s.equals("Review")) {
        	displayReview();
        }
        
        if (s.equals("Create Lease")) {
        	displayCreateLease();
        }
        
        if (s.equals("Check User")) {
        	displayCheckUser();
        }
        
        if (s.equals("Modify Contracts")) {
        	displayModifyContractsPanel();
        }
        
        if (s.equals("Manage Inventory")) {
        	displayManageInventory();
        }
        
        if (s.equals("Generate Reports")) {
        	updateGenerateReportsPanel();
        	displayGenerateReports();
        }
        
        /*
         * Not-Dashboard Buttons
         */
        
        if (s.equals("View Applications")) {
        	updateViewApplications();
        }
        
        if (s.equals("Early Termination")) {
        	try {
				Database.REarlyT(Integer.parseInt(contractUserIDField.getText()), Integer.parseInt(contractApartmentIDField.getText()), Integer.parseInt(contractHouseIDField.getText()));
			} catch (Exception e2) {
				System.out.println("Early termination failed.");
			}
        }
        
        if (s.equals("Terminate Contract")) {
        	try {
				Database.terminateContract(Integer.parseInt(contractUserIDField.getText()), Integer.parseInt(contractApartmentIDField.getText()), Integer.parseInt(contractHouseIDField.getText()));
			} catch (Exception e2) {
				System.out.println("Contract termination failed.");
			}
        }
        
        if (s.equals("Make Lease")) {
        	try {
				Database.CLease(Integer.parseInt(createLeaseUserID.getText()),Integer.parseInt(createLeaseApartmentID.getText()),Integer.parseInt(createLeaseHouseID.getText()), new ArrayList<Integer>(0),createLeaseOptions.getText());
			} catch (Exception e2) {
				System.out.println("Failed to make lease.");
			}
        }
        
        if (s.equals("Add Late Fee")) {
        	try {
				Database.AddLFees(Integer.parseInt(contractUserIDField.getText()), Integer.parseInt(contractApartmentIDField.getText()), Integer.parseInt(contractHouseIDField.getText()));
			} catch (Exception e2) {
				System.out.println("Failed to add late fees.");
			}
        }
        
        if (s.equals("Apply")) {
        	try {
        		// don't know where UserID's supposed to come from
        		// don't know an array of integers for roommates is coming from
        		// don't know where income is coming from
				Database.applicationRequest(1, new ArrayList<Integer>(), 1000, Integer.parseInt(apartmentIDtxtField.getText()), Integer.parseInt(apartmentIDtxtField.getText()), leaseOptiontxtField.getText());
			} catch (Exception e2) {
		    	roommatestxtField.setText("Roommates");
		    	apartmentIDtxtField.setText("Apartment/House ID");
		    	leaseOptiontxtField.setText("Lease Option");
			}
        }
        
        if (s.equals("Pay Bill")) {
        	try {
				Database.PayDues(Integer.parseInt(billIDtxtField.getText()));
			} catch (Exception e2) {
				System.out.println("Unable to pay bill.");
			}
        }
        
        if (s.equals("Make Review")) {
        	try {
        		// don't know where userID is to come from
				Database.AddReview(Integer.parseInt(reviewIDField.getText()), Integer.parseInt(reviewIDField.getText()), Integer.parseInt(reviewScoreField.getText()), 1);
			} catch (Exception e2) {
				System.out.println("Review submission failed.");
			}
        }
        
        if (s.equals("Add Apartment")) {
        	try {
        		ArrayList<String> temp = new ArrayList<String>();
        		temp.add(miApartmentIDField.getText());
        		temp.add(miApartmentNumberField.getText());
        		temp.add(miBuildingIDField.getText());
        		temp.add(miBedroomsField.getText());
        		temp.add(miBathroomsField.getText());
        		temp.add(miPriceField.getText());
        		temp.add(miLocationField.getText());
        		Database.addApartment(temp);
        		resetmiFields();
			} catch (Exception e2) {
	        	System.out.println("Unable to add apartment.");
			}
        }
        
        if (s.equals("Add House")) {
        	try {
        		ArrayList<String> temp = new ArrayList<String>();
        		temp.add(miHouseIDField.getText());
        		temp.add(miBedroomsField.getText());
        		temp.add(miBathroomsField.getText());
        		temp.add(miPriceField.getText());
        		temp.add(miLocationField.getText());
        		Database.addHouse(temp);
        		resetmiFields();
			} catch (Exception e2) {
	        	System.out.println("Unable to add house.");
			}
        }
        
        if (s.equals("Update Inventory")) {
        	try {
        		Database.updateListing(Integer.parseInt(miApartmentIDField.getText()), Integer.parseInt(miHouseIDField.getText()), Double.parseDouble(miPriceField.getText())); 
        		resetmiFields();
			} catch (Exception e2) {
	        	System.out.println("Unable to delete inventory.");
			}
        }
        
        if (s.equals("Delete Inventory")) {
        	try {
        		Database.deleteListing(Integer.parseInt(miApartmentIDField.getText()), Integer.parseInt(miHouseIDField.getText()));
        		resetmiFields();
			} catch (Exception e2) {
	        	System.out.println("Unable to update inventory.");
			}
        }
        
        if (s.equals("Check The User")) {
        	try {
        		String userIDString = checkUserIDField.getText();
        		int userID = 0;
        		try {
            		userID = Integer.parseInt(userIDString);
        		} catch (Exception e4) {
        		}
        		informationArea.setText("");
        		ArrayList<String> result = new ArrayList<String>();
            	result = Database.CheckDues(userID);
            	informationArea.append("Results: \n");
        		int i = 0;
        		for (String string : result) {
        			String totalString = "";
        			switch (i) {
					case 0:
						totalString = "\nBill ID: " + string;
						break;
					case 1:
						totalString = "\nBill Type: " + string;
						break;
					case 2:
						totalString = "\nPayment Status: " + string;
						break;
					case 3:
						totalString = "\nPenalty Fee: " + string;
						break;
					case 4:
						totalString = "\nUser ID: " + string + "\n";
						i = -1;
						break;
					default:
						break;
					}
        			i++;
        			informationArea.append(totalString);
        		}
        	} catch (Exception e2) {
        		e2.printStackTrace();
        		informationArea.setText("No data found.");
        	}
        	updateCheckUserPanel();
        }
        
        if (s.equals("Check History")) {
        	try {
        		informationArea.setText("");
				historyInformation = new ArrayList<String>();
        		int userID = 0;
        		try {
            		userID = Integer.parseInt(checkUserIDField.getText());
        		} catch (Exception e4) {
        		}
				historyInformation = Database.ChHistory(userID);
            	informationArea.append("History: \n");
        		int i = 0;
        		for (String string : historyInformation) {
        			String totalString = "";
        			switch (i) {
					case 0:
						totalString = "\nLease: " + string;
						break;
					case 1:
						totalString = "\n " + string;
						break;
					case 2:
						totalString = "" + string;
						i = -1;
						break;
					default:
						break;
					}
        			informationArea.append(totalString);
        			i++;
        		}
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("Check history failed.");
			}
        	updateCheckUserPanel();
        }
        
        if (s.equals("Details")) {
        	try {
        		detailStrings = new ArrayList<String>();
        		try {
        			detailStrings = Database.ViewHA(Integer.parseInt(apartmentIDField.getText()),Integer.parseInt(apartmentIDField.getText()));
				} catch (Exception e2) {
					if (Database.isOffline) {
						detailStrings = Database.ViewHA(1,1);
					}
				}
        	} catch (Exception e3) {
        		e3.printStackTrace();
        		System.out.println("Failed to find details of house/apartment: " + apartmentIDField.getText());
        	}
        	displayApartmentInfo();
        }
        
        /*
         * Search buttons
         */
        
        if (s.equals("Search")) {
        	try {
        		int houseOrApartment = 0;
        		if (houseTypeRadio.isSelected()) {
        			houseOrApartment = 1;
        		} else {
        			houseOrApartment = 0;
        		}
            	resultStrings = Database.Search(Integer.parseInt(bedroomsField.getText()), Integer.parseInt(bathroomsField.getText()), Float.parseFloat(priceField.getText()), houseOrApartment);
			} catch (Exception e2) {
				System.out.println("Search failed");
			}
        	displaySearchResults();
        }
        
        /*
         * Login buttons
         */
        
        if (s.equals("Signup")) {
        	ArrayList<String> signupData = new ArrayList<String>();
            signupAddress = signupAddresstxtField.getText();
            signupPassword = signupPasswordtxtField.getText();
            signupFirstName = signupFirstNametxtField.getText();
            signupLastName = signupLastNametxtField.getText();
            signupPhone = signupPhonetxtField.getText();
            signupEmail = signupEmailtxtField.getText();
            signupCity = signupCitytxtField.getText();
            signupState = signupStatetxtField.getText();
            signupZip = signupZiptxtField.getText();
            signupData.add(signupFirstName);
            signupData.add(signupLastName);
            signupData.add(signupPhone);
            signupData.add(signupEmail);
            signupData.add(signupCity);
            signupData.add(signupState);
            signupData.add(signupZip);
            signupData.add(signupAddress);
            signupData.add(signupPassword);
            try {
				Database.Registration(signupData);
	            userMessage.setText("Signed up! Proceed with login using Email: " + signupEmail + " and Password: " + signupPassword);
			} catch (Exception e1) {
	            userMessage.setText("Sign up failed.");
			}
        }
        
        if (s.equals("Login")) { 
        	Boolean success = true;
            System.out.print("Submitting login...");
            email = emailtxtField.getText();
            password = new String(passwordtxtField.getPassword()); // Passwords are stored as a char[]
            System.out.println("\nEmail: " + email + "\nPass: " + password);
            userMessage.setText("Logging in...");
        	try {
        		if (demoCheckBox.isSelected()) {
        			Database.isOffline = true;
        		}
				if (Database.Login(email, password))
					isLoggedIn = true;
				success = true;
			} catch (Exception e1) {
				success = false;
				//e1.printStackTrace();
			}
            if (email.equals("admin") && password.equals("pass")) {
            	isAdmin = true;
            }
        	if (success) {
        		setupPanels();
        		try {
                    //userInformation.setText("Balance: "+Database.ChBalance(001,1,1));
        			// commented out because Database.java doesn't really have a user information functionality
        			userInformation.setText("Welcome to UNF Apartments.");
        			if (isAdmin) {
        				userInformation.append("\nYou are an admin.");
        			}
        		} catch (Exception e2) {
        		}
            	displayDashboard();
                System.out.println("Logged in");
        	} else {
                userMessage.setText("Login failed.");
        	}
        } 
    } 
}
