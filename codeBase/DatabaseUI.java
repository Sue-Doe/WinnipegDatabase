package codeBase;
import java.util.Scanner;

//Purpose: Runs the UI for the database
public class DatabaseUI {

    Query query;

    public DatabaseUI() {
        query = new Query();
    }

    /**
     * First page of the cmd line interface
     * 
     * @param none
     * @return void
     */
    public void run() {

        Scanner scanner = new Scanner(System.in);

        boolean looping = true;
        while (looping) {
            System.out.println("Welcome to the City of Winnipeg Ward Database:");
            System.out.println("press 1 to Start");
            System.out.println("press q to quit");

            System.out.print("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                viewData(scanner);

            } else if (choice.equals("2")) {

            } else if (choice.equals("3")) {

            }

            else if (choice.equals("q")) {
                looping = false;
            }
        }

        scanner.close();
    }

    /**
     * prints the different Information that can be accessed
     * 
     * @param none
     * @return void
     */
    public void viewData(Scanner scanner) {

        boolean looping = true;
        while (looping) {
            System.out.println("Please Select An Option:");
            System.out.println("1 - Access Permit Information");
            System.out.println("2 - Access Public Notice Information");
            System.out.println("3 - Access WFPS Call Information");
            System.out.println("4 - Access By-Law Call Information");
            System.out.println("5 - Access General Election Information");
            System.out.println("6 - Access Candidate Information");
            System.out.println("7 - Access Councilor Information");
            System.out.println("8 - Access Gift Information");
            System.out.println("9 - Access Ward Information");
            System.out.println("x - Administration");
            System.out.println("b - Back");
            System.out.println("");

            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                permitData(scanner);
            } else if (choice.equals("2")) {
                publicNoticeData(scanner);
            } else if (choice.equals("3")) {
                wfpsCallData(scanner);
            } else if (choice.equals("4")) {
                byLawData(scanner);
            } else if (choice.equals("5")) {
                generalElectionData(scanner);
            } else if (choice.equals("6")) {
                candidateData(scanner);
            } else if (choice.equals("7")) {
                councilorData(scanner);
            } else if (choice.equals("8")) {
                giftData(scanner);
            } else if (choice.equals("9")) {
                wardData(scanner);
            } else if (choice.equals("x")) {
                adminData(scanner);
            } else if (choice.equals("b")) {
                looping = false;
            }
        }

    }

    /**
     * prints the permit Information that can be accessed
     * 
     * @param none
     * @return void
     */
    public void permitData(Scanner scanner) {
        boolean looping = true;

        while (looping) {

            System.out.println("1 - Search By Permit Number");
            System.out.println("2 - Find permits with an Address");
            System.out.println("3 - Find number of issued permits per Ward and type");
            System.out.println("b - Back");

            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                System.out.println("Please enter the Permit Number: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchPermit(choice);
                System.out.println("");

            } else if (choice.equals("2")) {
                System.out.println("Please enter an Address: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchPermitByAddress(choice);
                System.out.println("");
            } else if (choice.equals("3")) {
                query.countPermitsByTypeAndWard();
            } else if (choice.equals("b")) {
                looping = false;
            }
        }

    }

    /**
     * prints the publicNotice Information that can be accessed
     * 
     * @param none
     * @return void
     */
    public void publicNoticeData(Scanner scanner) {
        boolean looping = true;

        while (looping) {

            System.out.println("1 - Search By public notice Number");
            System.out.println("2 - Find public notice with an Address");
            System.out.println("3 - Find most common complaint in a ward between two months");
            System.out.println("b - Back");

            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                System.out.println("Please enter the public Notice Number: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchNotice(choice);
                System.out.println("");

            } else if (choice.equals("2")) {
                System.out.println("Please enter an Address: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchNoticeByAddress(choice);
                System.out.println("");

            } else if (choice.equals("3")) {
                System.out.println("Please enter a ward: ");
                String wardname = scanner.nextLine();
                System.out.println("");

                System.out.println("Please enter a Month (mm): ");
                String monthOne = scanner.nextLine();
                System.out.println("");

                System.out.println("Please enter another Month: (mm) ");
                String monthTwo = scanner.nextLine();
                System.out.println("");

                query.mostCommonComplaintTypes(wardname, monthOne, monthTwo);
                System.out.println("");
            }

            else if (choice.equals("b")) {
                looping = false;
            }
        }
    }

    /**
     * prints the WFPS Call Information that can be accessed
     * 
     * @param none
     * @return void
     */
    void wfpsCallData(Scanner scanner) {
        boolean looping = true;

        while (looping) {

            System.out.println("1 - Search By Incident Number");
            System.out.println("2 - Search by Date and Incident Type");
            System.out.println("3 - List the Wards with the Most Calls");
            System.out.println("4 - List the Wards with total number of each incident calls");
            System.out.println("b - Back");

            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                System.out.println("Please enter the Incident Number: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchWFPSCall(choice);
                System.out.println("");

            } else if (choice.equals("2")) {
                System.out.println("Please enter a date mm/dd/yyyy");
                String date = scanner.nextLine();
                System.out.println("Please enter an Incident Type");
                String type = scanner.nextLine();
                query.searchSpecficWFPSCall(date, type);
                System.out.println("");
            } else if (choice.equals("3")) {
                System.out.println("Please enter desired return amount ");
                choice = scanner.nextLine();
                query.listTopWardsWithMostWFPS(choice);
            } else if (choice.equals("4")) {
                query.countWFPSByIncidentTypeAndWard();
            }

            else if (choice.equals("b")) {
                looping = false;
            }
        }
    }

    /**
     * prints the byLaw Information that can be accessed
     * 
     * @param none
     * @return void
     */
    void byLawData(Scanner scanner) {
        boolean looping = true;

        while (looping) {

            System.out.println("1 - Search By RSN Number");
            System.out.println("2 - Search by Date");
            System.out.println("b - Back");

            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                System.out.println("Please enter the RSN Number: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchByLaw(choice);
                System.out.println("");

            } else if (choice.equals("2")) {
                System.out.println("Please enter a date mm/dd/yyyy");
                choice = scanner.nextLine();
                query.searchByLawOnDay(choice);
                System.out.println("");
            }

            else if (choice.equals("b")) {
                looping = false;
            }
        }
    }

    /**
     * prints the General Election Information that can be accessed
     * 
     * @param none
     * @return void
     */
    void generalElectionData(Scanner scanner) {
        boolean looping = true;

        while (looping) {

            System.out.println("1 - Search By Election ID");
            System.out.println("2 - Return latest elections");
            System.out.println("b - Back");

            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                System.out.println("Please enter the Election ID: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchGeneralElection(choice);
                System.out.println("");

            } else if (choice.equals("2")) {
                System.out.println("Please the number of elections to be returned");
                choice = scanner.nextLine();
                query.newToOldGeneralElection(choice);
                System.out.println("");
            }

            else if (choice.equals("b")) {
                looping = false;
            }
        }
    }

    /**
     * prints the candidate Information that can be accessed
     * 
     * @param none
     * @return void
     */
    void candidateData(Scanner scanner) {
        boolean looping = true;

        while (looping) {

            System.out.println("1 - Search By Candidate ID");
            System.out.println("2 - Search By Candidate Name");
            System.out.println("b - Back");

            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                System.out.println("Please enter the Candidate ID: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchCanidate(choice);
                System.out.println("");

            } else if (choice.equals("2")) {
                System.out.println("Please the name of the candidate");
                choice = scanner.nextLine();
                query.searchCanidateByName(choice);
                System.out.println("");
            }

            else if (choice.equals("b")) {
                looping = false;
            }
        }

    }

    /**
     * prints the councilor Information that can be accessed
     * 
     * @param none
     * @return void
     */
    void councilorData(Scanner scanner) {
        boolean looping = true;

        while (looping) {

            System.out.println("1 - Search By Councilor ID");
            System.out.println("2 - Search By Councilor Name");
            System.out.println("b - Back");

            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                System.out.println("Please enter the Councilor ID: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchCouncilor(choice);
                System.out.println("");

            } else if (choice.equals("2")) {
                System.out.println("Please the name of the councilor");
                choice = scanner.nextLine();
                query.searchCouncilorByName(choice);
                System.out.println("");
            }

            else if (choice.equals("b")) {
                looping = false;
            }
        }

    }

    /**
     * prints the gift Information that can be accessed
     * 
     * @param none
     * @return void
     */
    void giftData(Scanner scanner) {
        boolean looping = true;

        while (looping) {

            System.out.println("1 - Search By Gift ID");
            System.out.println("2 - Search By Councilor Name");
            System.out.println("3 - Number of Gifts by Each Councilor");
            System.out.println("b - Back");

            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                System.out.println("Please enter the Gift ID: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchGift(choice);
                ;
                System.out.println("");

            } else if (choice.equals("2")) {
                System.out.println("Please the name of the councilor");
                choice = scanner.nextLine();
                query.searchCouncilorGifts(choice);
                System.out.println("");
            } else if (choice.equals("3")) {
                query.countGiftsPerCouncilor();
                System.out.println("");
            }

            else if (choice.equals("b")) {
                looping = false;
            }
        }

    }

    /**
     * prints the ward Information that can be accessed
     * 
     * @param none
     * @return void
     */
    void wardData(Scanner scanner) {
        boolean looping = true;

        while (looping) {

            System.out.println("1 - Search By Ward ID");
            // System.out.println("2 - Find the top number of Streets that have permits ");
            System.out.println("b - Back");

            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();
            System.out.println("");

            if (choice.equals("1")) {
                System.out.println("Please enter the Ward ID: ");
                choice = scanner.nextLine();
                System.out.println("");
                query.searchWard(choice);
                System.out.println("");
            }

            else if (choice.equals("b")) {
                looping = false;
            }
        }

    }

    /**
     * prints the Adminisrator Information that can be accessed
     * 
     * @param none
     * @return void
     */
    void adminData(Scanner scanner) {
        boolean looping = true;

        while (looping) {

            System.out.println("1 - Delete all Data");
            System.out.println("2 - Populate DataBase");
            System.out.println("b - Back");

            System.out.println("");
            System.out.println("Please enter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.println("Please Confirm (Y/n)");
                choice = scanner.nextLine();
                if (choice.equals("y") || choice.equals("Y") || choice.equals(""))
                    query.deleteAll();
                System.out.println("");
            } else if (choice.equals("2")) {
                System.out.println("Please Confirm (Y/n)");
                choice = scanner.nextLine();
                if (choice.equals("y") || choice.equals("Y") || choice.equals(""))
                    query.populateAll();
                System.out.println("");
            }

            else if (choice.equals("b")) {
                looping = false;
            }
        }
    }

}