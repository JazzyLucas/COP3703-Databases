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
 * 
 * @author JazzyLucas
 */

class UserInterface extends JFrame implements ActionListener { 
	private static final long serialVersionUID = -1779981392814660970L; // Ignore this
	
	private static boolean isAdmin = false;
	public static boolean isLoggedIn = false;
	private static boolean hasSearched = false;
	
	/*
	 * Main stuff
	 * 
	 * Here's an explanation of these layouts:
	 * Frames hold a panel. Panels can contain other panels, but more importantly, panels can contain gridlayouts!
	 * We'll be using panels & layouts INSIDE a main panel to display all of the necessary "pages".
	 */
    static JFrame mainFrame; 
    static JPanel mainPanel;
    static JButton dashboardButton; // We'll use this to go to the dashboard from everything else
	
	// Login Elements
	static JPanel loginPanel;
	static String username;
	static String password;
    static JTextField usernametxtField;
    static JPasswordField passwordtxtField;
    static JLabel userMessage;
    static JButton signupButton;
    static JButton subButton;
	static String signupUsername;
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
    static JTextField signupUsernametxtField;
    static JTextField signupPasswordtxtField;
    
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
	
	// Leases Elements
    static JPanel leasePanel;
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
    static JButton checkLeaseButton;
    
    // Contract Modification Elements
    static JPanel contractModificationPanel;
    static JTextField contractUserIDField;
    static JTextField contractApartmentIDField;
    static JTextField contractHouseIDField;
    static JButton earlyTerminationButton;
    static JButton terminateContractButton;
    
    // ManageInventory Elements
    static JPanel manageInventoryPanel;
    // (Might need some text fields)
	static JButton addInventoryButton;
	static JButton deleteInventoryButton;
	static JButton updateInventoryButton;
    
    // GenerateReports Elements
    static JPanel generateReportsPanel;
    static JTextArea reportsTextArea;
    
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
        
        // Create an instance of this UI and set up events
        mainFrame.setSize(900, 500); 
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
        loginPanel = new JPanel();
        BoxLayout layout = new BoxLayout(loginPanel, BoxLayout.Y_AXIS);
        //GridLayout layout = new GridLayout(5,1); // 20 rows, 1 column main layout
    	loginPanel.setLayout(layout);
    	JPanel panel1 = new JPanel();
    	JPanel panel2 = new JPanel();
    	panel1.setLayout(new GridLayout(2,2));
    	panel2.setLayout(new GridLayout(9,2));
    	// Set up login panel
        JLabel usernameLabel = new JLabel("Username: "); 
        JLabel passwordLabel = new JLabel("Password: ");
        usernametxtField = new JTextField("", 16);
        passwordtxtField = new JPasswordField("", 16);
        usernameLabel.setLabelFor(usernametxtField);
        passwordLabel.setLabelFor(passwordtxtField);
    	JLabel loginTitle = new JLabel("Welcome to the Apartment Database");
    	loginTitle.setHorizontalAlignment(JLabel.CENTER);
        userMessage = new JLabel("");
    	userMessage.setHorizontalAlignment(JLabel.CENTER);
        subButton = new JButton("Login");
        signupButton = new JButton("Signup");
        subButton.addActionListener(UI); 
        signupButton.addActionListener(UI);
        loginPanel.add(loginTitle);
        panel1.add(usernameLabel);
        panel1.add(usernametxtField);
        panel1.add(passwordLabel);
        panel1.add(passwordtxtField);
        // Set up signup panel
        JLabel signupUsernameLabel = new JLabel("Username: "); 
        JLabel signupPasswordLabel = new JLabel("Password: ");
        JLabel signupFirstNameLabel = new JLabel("First Name: "); 
        JLabel signupLastNameLabel = new JLabel("Last Name: ");
        JLabel signupPhoneLabel = new JLabel("Phone: "); 
        JLabel signupEmailLabel = new JLabel("Email: ");
        JLabel signupCityLabel = new JLabel("City: "); 
        JLabel signupStateLabel = new JLabel("State: ");
        JLabel signupZipLabel = new JLabel("Zip: "); 
        signupUsernametxtField = new JTextField("", 16);
        signupPasswordtxtField = new JTextField("", 16);
        signupFirstNametxtField = new JTextField("", 20);
        signupLastNametxtField = new JTextField("", 20);
        signupPhonetxtField = new JTextField("", 10);
        signupEmailtxtField = new JTextField("", 20);
        signupCitytxtField = new JTextField("", 20);
        signupStatetxtField = new JTextField("", 20);
        signupZiptxtField = new JTextField("", 5);
        signupUsernameLabel.setLabelFor(signupUsernametxtField);
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
    	panel2.add(signupUsernameLabel);
    	panel2.add(signupUsernametxtField);
    	panel2.add(signupPasswordLabel);
    	panel2.add(signupPasswordtxtField);
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
    	// Put it all together
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(panel1);
        loginPanel.add(userMessage);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(subButton);
        loginPanel.add(Box.createVerticalStrut(10));
    	loginPanel.add(signupTitle);
        loginPanel.add(Box.createVerticalStrut(10));
    	loginPanel.add(panel2);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(signupButton);
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
    	dashboardPanel.add(signOutButton);
    	
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
    	leaseInfos = new ArrayList<JTextField>();
    	JLabel leaseTitle = new JLabel("Leases");
    	leaseTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        BoxLayout leaseLayout = new BoxLayout(leasePanel, BoxLayout.Y_AXIS);
        leasePanel.setLayout(leaseLayout);
        leasePanel.add(Box.createVerticalStrut(10));
    	leasePanel.add(leaseTitle);
        leasePanel.add(Box.createVerticalStrut(10));
        leasePanel.add(dashboardButton);
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
        // Had some issues with adding the dashboard button, so this is a fix
        JButton tempDashboardButton = new JButton("Dashboard");
        tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchPanel.add(tempDashboardButton);
        searchPanel.add(Box.createVerticalStrut(10));
    }
    
    private static void setupPaymentsPanel() {
        paymentsPanel = new JPanel();
        paymentsInfoPanel = new JPanel();
        BoxLayout paymentsLayout = new BoxLayout(paymentsPanel, BoxLayout.Y_AXIS);
        paymentsPanel.setLayout(paymentsLayout);
        JLabel paymentsTitle = new JLabel("Payments");
        paymentsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        paymentsPanel.add(Box.createVerticalStrut(10));
        paymentsPanel.add(paymentsTitle);
        paymentsPanel.add(Box.createVerticalStrut(10));
        JButton tempDashboardButton = new JButton("Dashboard");
        tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        paymentsPanel.add(tempDashboardButton);
        paymentsPanel.add(Box.createVerticalStrut(10));
    	// Initializations
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
        createLeasePanel.add(createLeaseTitle);
        createLeasePanel.add(panel1);
        makeLeaseButton = new JButton("Make Lease");
        makeLeaseButton.addActionListener(UI);
        makeLeaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createLeasePanel.add(makeLeaseButton);
        JButton tempDashboardButton = new JButton("Dashboard");
        tempDashboardButton.addActionListener(UI);
        tempDashboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
    	informationArea = new JTextArea("Information will be displayed here.");
    	informationPanel = new JPanel();
    	fieldsPanel.add(userIDLabel);
    	fieldsPanel.add(checkUserIDField);
    	fieldsPanel.add(leaseIDLabel);
    	fieldsPanel.add(checkLeaseIDField);
    	checkUserPanel.add(checkUserTitle);
    	checkUserPanel.add(Box.createVerticalStrut(10));
    	checkUserPanel.add(fieldsPanel);
    	checkUserPanel.add(Box.createVerticalStrut(10));
    	checkUserButton_ = new JButton("Check The User");
    	checkUserButton_.addActionListener(UI);
    	checkUserButton_.setAlignmentX(Component.CENTER_ALIGNMENT);
    	checkUserPanel.add(checkUserButton_);
    	checkLeaseButton = new JButton("Check Lease");
    	checkLeaseButton.addActionListener(UI);
    	checkLeaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	checkUserPanel.add(checkLeaseButton);
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
    	JLabel contractModificationTitle = new JLabel("Check a User or Lease");
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
    	contractModificationPanel.add(contractModificationTitle);
    	contractModificationPanel.add(Box.createVerticalStrut(10));
    	contractModificationPanel.add(fieldsPanel);
    	contractModificationPanel.add(Box.createVerticalStrut(10));
    	earlyTerminationButton = new JButton("Early Termination");
    	earlyTerminationButton.addActionListener(UI);
    	earlyTerminationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	contractModificationPanel.add(earlyTerminationButton);
    	terminateContractButton = new JButton("Terminate Contract");
    	terminateContractButton.addActionListener(UI);
    	terminateContractButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	contractModificationPanel.add(terminateContractButton);
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
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    	manageInventoryPanel.add(tempDashboardButton);
        addInventoryButton = new JButton("Add Inventory");
        deleteInventoryButton = new JButton("Delete Inventory");
        updateInventoryButton = new JButton("Update Inventory");
    	addInventoryButton.addActionListener(UI);
    	deleteInventoryButton.addActionListener(UI);
    	updateInventoryButton.addActionListener(UI);
    	addInventoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	deleteInventoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	updateInventoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    	manageInventoryPanel.add(addInventoryButton);
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    	manageInventoryPanel.add(deleteInventoryButton);
    	manageInventoryPanel.add(Box.createVerticalStrut(10));
    	manageInventoryPanel.add(updateInventoryButton);
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
    	reportsTextArea = new JTextArea("[Generated Report]");
    	reportsTextArea.setEditable(false);
    	generateReportsPanel.add(Box.createVerticalStrut(10));
    	generateReportsPanel.add(reportsTextArea);
    }
    
    private static void updateGenerateReportsPanel() {
    	generateReportsPanel.revalidate();
    	generateReportsPanel.repaint();
    }
    
    private static void updateCheckUserPanel() {
    	checkUserPanel.revalidate();
    	checkUserPanel.repaint();
    }
    
    private static void updatePaymentsPanel() {
    	//TODO: Query the database for the user's leases.
    	for (Component c : paymentsInfoPanel.getComponents()) {
    		paymentsInfoPanel.remove(c);
    	}
    	if (paymentInfos.isEmpty()) {
            paymentsInfoPanel.add(Box.createVerticalStrut(10));
    		paymentsInfoPanel.add(new JTextField("No payments/leases yet."));
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
			// dw about this
			e.printStackTrace();
		}
    	paymentsPanel.revalidate();
    	paymentsPanel.repaint();
    }
    
    private static void updateLeasePanel() {
    	//TODO: Query the database for the user's leases.
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
    	for (String string : leaseInfoStrings) {
			int PIDInt = Integer.parseInt(string);
			ArrayList<String> tempList = new ArrayList<String>();
			try {
				tempList = Database.propertyDetails(PIDInt, 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
					apartmentInfo.append("\nLease Options: " + displayThing);
					i = 0;
					break;
				}
			}
    	}

        if (!hasSearched) {
            resultsPanel = new JPanel();
            //apartmentInfo = new JTextArea("Nothing found.");
            apartmentInfo.setEditable(false);
        }
        
        for (Component c : resultsPanel.getComponents()) {
            resultsPanel.remove(c);
        }
        resultsPanel.add(apartmentInfo);
        // and for now...
        if (!hasSearched) {
            searchPanel.add(resultsPanel);
        }
        resultsPanel.revalidate();
        resultsPanel.repaint();
        searchPanel.revalidate();
        searchPanel.repaint();
        mainPanel.revalidate();
        mainPanel.repaint();
        hasSearched = true;
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
        	// TODO: Generate reports
        	reportsTextArea.setText("[Generated Report]");
        	updateGenerateReportsPanel();
        	displayGenerateReports();
        }
        
        /*
         * Not-Dashboard Buttons
         */
        
        if (s.equals("Make Payment")) {
        	// TODO: make an SQL payment thing
        }
        
        if (s.equals("Make Review")) {
        	// TODO: send a review to the SQL thing
        }
        
        if (s.equals("Add Inventory")) {
        	// TODO
        }
        
        if (s.equals("Delete Inventory")) {
        	// TODO
        }
        
        if (s.equals("Update Inventory")) {
        	// TODO
        }
        
        if (s.equals("Check The User")) {
        	// TODO
        	informationArea.setText("Checked User: ");
        	updateCheckUserPanel();
        }
        
        if (s.equals("Check Lease")) {
        	// TODO
        	informationArea.setText("Checked Lease: ");
        	updateCheckUserPanel();
        }
        
        /*
         * Search buttons
         */
        
        if (s.equals("Search")) {
        	// TODO: show up (5? 3? idk?) results as a textarea
        	// TODO: get the itemEvent for the JRadios and store them as an int
        	try {
            	leaseInfoStrings = Database.Search(Integer.parseInt(bedroomsField.getText()), Integer.parseInt(bathroomsField.getText()), Float.parseFloat(priceField.getText()), 0);
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
            signupUsername = signupUsernametxtField.getText();
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
            signupData.add(signupUsername);
            signupData.add(signupPassword);
            try {
				Database.Registration(signupData);
	            userMessage.setText("Signed up! Proceed with login using Username: " + signupUsername + " and Password: " + signupPassword);
			} catch (Exception e1) {
	            userMessage.setText("Sign up failed.");
			}
        }
        
        if (s.equals("Login")) { 
            System.out.print("Submitting login...");
            username = usernametxtField.getText();
            password = new String(passwordtxtField.getPassword()); // Passwords are stored as a char[]
            System.out.println("\nUsername: " + username + "\nPass: " + password);
            userMessage.setText("Logging in...");
        	try {
				//if (Database.Login(username, password))
					isLoggedIn = true;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            if (username.equals("admin") && password.equals("pass")) {
            	isAdmin = true;
            }
            setupPanels();
            // TODO:
            // ChHistory?
            // ChLease?
            // ChBalance?
            // then change userInformation's text data to that?
        	displayDashboard();
            System.out.println("Logged in");
            // else if (database is down)
            userMessage.setText("The database is down, please try again later.");
            // else
            userMessage.setText("Login failed.");
        } 
    } 
}