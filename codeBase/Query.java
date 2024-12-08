package codeBase;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//Purpose: execute and print Queries
public class Query {

    Connection connection;

    public Query() {

        this.connection = SQLServer.run();
    }

    /**
     * Query a specfic Permit from a given Permit ID
     * printing the results
     * 
     * @param input - Permit ID
     * @return void
     */
    void searchPermit(String input) {
        String query = "SELECT * FROM Permits WHERE PermitNumber = ?;";
        String validateString = SafeInput.validateStringUserInput(input);

        if (validateString == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, input);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s", "Permit Number",
                            "Permit Type", "IssueDate", "Ward ID", "Address"));
                    System.out.println(String.join("", java.util.Collections.nCopies(120, "-")));

                    while (resultSet.next()) {
                        String address = resultSet.getInt("StreetNumber") + " " + resultSet.getString("StreetName");

                        System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("PermitNumber")),
                                SafeInput.shrinkString(resultSet.getString("PermitType")),
                                SafeInput.shrinkString(resultSet.getString("IssueDate")),
                                SafeInput.shrinkString(resultSet.getString("WardID")),
                                SafeInput.shrinkString(address)));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(validateString);
        }
    }

    /**
     * Query a specfic public Notice from a given Notice ID
     * printing the results
     * 
     * @param input - Notice ID
     * @return void
     */
    void searchNotice(String input) {
        String query = "SELECT * FROM PublicNotices WHERE NoticeNum = ?;";
        String validateString = SafeInput.validateStringUserInput(input);
        if (validateString == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, input);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s", "Notice Number",
                            "Notice Type", "Order Date", "Address", "WardID"));
                    System.out.println(String.join("", java.util.Collections.nCopies(120, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("NoticeNum")),
                                SafeInput.shrinkString(resultSet.getString("NoticeType")),
                                SafeInput.shrinkString(resultSet.getString("OrderDate")),
                                SafeInput.shrinkString(resultSet.getString("NoticeAddress")),
                                SafeInput.shrinkString(resultSet.getString("WardID"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(validateString);
        }
    }

    /**
     * Query a specfic public Notice from a given Notice ID
     * printing the results
     * 
     * @param input - Notice ID
     * @return void
     */
    void searchByLaw(String input) {
        String query = "SELECT * FROM ByLaw WHERE RSN = ?;";
        int validateInt = SafeInput.validateIntegerUserInput(input);
        if (validateInt != Integer.MIN_VALUE) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, input);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s", "RSN", "NBHD Name",
                            "Complaint Type", "In Date", "WardID"));
                    System.out.println(String.join("", java.util.Collections.nCopies(120, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("RSN")),
                                SafeInput.shrinkString(resultSet.getString("NBHDName")),
                                SafeInput.shrinkString(resultSet.getString("ComplainType1")),
                                SafeInput.shrinkString(resultSet.getString("InDate")),
                                SafeInput.shrinkString(resultSet.getString("WardID"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: incorrect input");
        }
    }

    /**
     * Query a specfic WFPS Call from a given Notice Number
     * printing the results
     * 
     * @param input - Incident ID
     * @return void
     */
    void searchWFPSCall(String input) {
        String query = "SELECT * FROM WFPSCALLS WHERE IncidentNumber = ?;";
        int validateInt = SafeInput.validateIntegerUserInput(input);
        if (validateInt != Integer.MIN_VALUE) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, input);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s | %-20s", "Incident Number",
                            "Incident Type", "Call Time", "MotorVehicleIncident", "Units", "WardID"));
                    System.out.println(String.join("", java.util.Collections.nCopies(150, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("IncidentNumber")),
                                SafeInput.shrinkString(resultSet.getString("IncidentType")),
                                SafeInput.shrinkString(resultSet.getString("CallTime")),
                                SafeInput.shrinkString(resultSet.getString("MotorVehicleIncident")),
                                SafeInput.shrinkString(resultSet.getString("Units")),
                                SafeInput.shrinkString(resultSet.getString("WardID"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: incorrect input");
        }
    }

    /**
     * Query a specfic gift from a given gift ID
     * printing the results
     * 
     * @param input - gift ID
     * @return void
     */
    void searchGift(String input) {
        String query = "SELECT * FROM Gift WHERE GiftID = ?;";
        int validateInt = SafeInput.validateIntegerUserInput(input);
        if (validateInt != Integer.MIN_VALUE) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, input);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-30s | %-20s", "Gift ID", "Date Gifted",
                            "Reason", "Nature/Value"));
                    System.out.println(String.join("", java.util.Collections.nCopies(150, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-30s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("GiftID")),
                                SafeInput.shrinkString(resultSet.getString("DateGifted")),
                                SafeInput.shrinkString(resultSet.getString("Reason")),
                                SafeInput.shrinkString(resultSet.getString("NatureValue"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: incorrect input");
        }
    }

    /**
     * Query a specfic Councilor from a given councilorID
     * printing the results
     * 
     * @param input - councilorID
     * @return void
     */
    void searchCouncilor(String input) {
        String query = "SELECT * FROM Councilor WHERE CouncilorID = ?;";
        int validateInt = SafeInput.validateIntegerUserInput(input);
        if (validateInt != Integer.MIN_VALUE) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, input);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(
                            String.format("%-20s | %-20s | %-20s", "Councilor ID", "Councilor Name", "Ward ID"));
                    System.out.println(String.join("", java.util.Collections.nCopies(80, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("CouncilorID")),
                                SafeInput.shrinkString(resultSet.getString("CouncilorName")),
                                SafeInput.shrinkString(resultSet.getString("WardID"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: Invalid Input");
        }
    }

    /**
     * Query a specfic candidate from a given candidateID
     * printing the results
     * 
     * @param input - candidateID
     * @return void
     */
    void searchCanidate(String input) {
        String query = "SELECT * FROM Candidate WHERE CandidateID = ?;";
        int validateInt = SafeInput.validateIntegerUserInput(input);
        if (validateInt != Integer.MIN_VALUE) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, validateInt);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s", "Candidate ID",
                            "Candidate Name", "Won", "Votes", "CandDate"));
                    System.out.println(String.join("", java.util.Collections.nCopies(80, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("CandidateID")),
                                SafeInput.shrinkString(resultSet.getString("CandidateName")),
                                SafeInput.shrinkString(resultSet.getString("Won")),
                                SafeInput.shrinkString(resultSet.getString("Votes")),
                                SafeInput.shrinkString(resultSet.getString("CandDate"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: Invalid Input");
        }
    }

    /**
     * Query a specfic General Election from a given electionID
     * printing the results
     * 
     * @param input - electionID
     * @return void
     */
    void searchGeneralElection(String input) {
        String query = "SELECT * FROM GeneralElection WHERE ElectionID = ?;";
        int validateInt = SafeInput.validateIntegerUserInput(input);
        if (validateInt != Integer.MIN_VALUE) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, input);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-30s | %-20s", "Election ID", "Election Date",
                            "Position", "Ward ID"));
                    System.out.println(String.join("", java.util.Collections.nCopies(150, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-30s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("ElectionID")),
                                SafeInput.shrinkString(resultSet.getString("ElectionDate")),
                                SafeInput.shrinkString(resultSet.getString("Position")),
                                SafeInput.shrinkString(resultSet.getString("WardID"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: incorrect input");
        }
    }

    /**
     * Query a specfic Ward from a given WardID
     * printing the results
     * 
     * @param input - WardID
     * @return void
     */
    void searchWard(String input) {
        String query = "SELECT * FROM Ward WHERE WardID = ?;";
        int validateInt = SafeInput.validateIntegerUserInput(input);
        if (validateInt != Integer.MIN_VALUE) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, input);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s", "Ward ID", "Ward Name"));
                    System.out.println(String.join("", java.util.Collections.nCopies(50, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s ",
                                SafeInput.shrinkString(SafeInput.shrinkString(resultSet.getString("WardID"))),
                                SafeInput.shrinkString(SafeInput.shrinkString(resultSet.getString("Wardname")))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: incorrect input");
        }
    }

    /**
     * search for a permit from a full or partial address
     * printing the results
     * 
     * @param input - full or partial address
     * @return void
     */
    void searchPermitByAddress(String input) {
        String query = "SELECT * FROM Permits WHERE StreetName LIKE ?;";
        String validateString = SafeInput.validateStringUserInput(input);

        if (validateString == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + input + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-20s | %-20s | %-20s", "Permit Number",
                            "Permit Type", "IssueDate", "Ward ID", "Address"));
                    System.out.println(String.join("", java.util.Collections.nCopies(100, "-")));

                    while (resultSet.next()) {
                        String address = resultSet.getInt("StreetNumber") + " " + resultSet.getString("StreetName");

                        System.out.println(String.format("%-20s | %-20s | %-20s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("PermitNumber")),
                                SafeInput.shrinkString(resultSet.getString("PermitType")),
                                SafeInput.shrinkString(resultSet.getString("IssueDate")),
                                SafeInput.shrinkString(resultSet.getString("WardID")),
                                SafeInput.shrinkString(address)));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(validateString);
        }
    }

    /**
     * search for a Notice from a full or partial address
     * printing the results
     * 
     * @param input - full or partial address
     * @return void
     */
    void searchNoticeByAddress(String input) {
        String query = "SELECT * FROM PublicNotices WHERE NoticeAddress LIKE ?;";
        String validateString = SafeInput.validateStringUserInput(input);
        if (validateString == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + input + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-20s | %-20s | %-20s", "Notice Number",
                            "Notice Type", "Order Date", "Address", "WardID"));
                    System.out.println(String.join("", java.util.Collections.nCopies(100, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-20s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("NoticeNum")),
                                SafeInput.shrinkString(resultSet.getString("NoticeType")),
                                SafeInput.shrinkString(resultSet.getString("OrderDate")),
                                SafeInput.shrinkString(resultSet.getString("NoticeAddress")),
                                SafeInput.shrinkString(resultSet.getString("WardID"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(validateString);
        }
    }

    /**
     * search for By Law's on a specific date
     * printing the results
     * 
     * @param input - mm/dd/yyyy date format
     * @return void
     */
    void searchByLawOnDay(String input) {
        String query = "SELECT * FROM ByLaw WHERE InDate = ?;";
        String validateString = SafeInput.validateStringUserInput(input);
        if (validateString == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, input);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s", "RSN", "NBHD Name",
                            "Complaint Type", "In Date", "WardID"));
                    System.out.println(String.join("", java.util.Collections.nCopies(120, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("RSN")),
                                SafeInput.shrinkString(resultSet.getString("NBHDName")),
                                SafeInput.shrinkString(resultSet.getString("ComplainType1")),
                                SafeInput.shrinkString(resultSet.getString("InDate")),
                                SafeInput.shrinkString(resultSet.getString("WardID"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(validateString);
        }
    }

    /**
     * List the most recent General Election and their information
     * printing the results
     * 
     * @param input - Number of results wanted to be returned
     * @return void
     */
    void newToOldGeneralElection(String input) {
        String query = "SELECT TOP (?) * FROM GeneralElection ORDER BY ElectionDate DESC";
        int validateInt = SafeInput.validateIntegerUserInput(input);
        if (validateInt != Integer.MIN_VALUE) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, validateInt);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-30s | %-20s", "Election ID", "Election Date",
                            "Position", "Ward ID"));
                    System.out.println(String.join("", java.util.Collections.nCopies(150, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-30s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("ElectionID")),
                                SafeInput.shrinkString(resultSet.getString("ElectionDate")),
                                SafeInput.shrinkString(resultSet.getString("Position")),
                                SafeInput.shrinkString(resultSet.getString("WardID"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: incorrect input");
        }
    }

    /**
     * search for a candidate from a full or partial name
     * printing the results
     * 
     * @param input - full or partial name
     * @return void
     */
    void searchCanidateByName(String input) {
        String query = "SELECT * FROM Candidate WHERE CandidateName LIKE ?;";
        String validateString = SafeInput.validateStringUserInput(input);
        if (validateString == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + input + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-20s | %-20s | %-20s", "Candidate ID",
                            "Candidate Name", "Won", "Votes", "CandDate"));
                    System.out.println(String.join("", java.util.Collections.nCopies(120, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-20s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("CandidateID")),
                                SafeInput.shrinkString(resultSet.getString("CandidateName")),
                                SafeInput.shrinkString(resultSet.getString("Won")),
                                SafeInput.shrinkString(resultSet.getString("Votes")),
                                SafeInput.shrinkString(resultSet.getString("CandDate"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(validateString);
        }
    }

    /**
     * search for a Councilor from a full or partial name
     * printing the results
     * 
     * @param input - full or partial name
     * @return void
     */
    void searchCouncilorByName(String input) {
        String query = "SELECT * FROM Councilor WHERE CouncilorName LIKE ?;";
        String validateString = SafeInput.validateStringUserInput(input);
        if (validateString == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + input + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(
                            String.format("%-20s | %-20s | %-20s", "Councilor ID", "Councilor Name", "Ward ID"));
                    System.out.println(String.join("", java.util.Collections.nCopies(60, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("CouncilorID")),
                                SafeInput.shrinkString(resultSet.getString("CouncilorName")),
                                SafeInput.shrinkString(resultSet.getString("WardID"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(validateString);
        }
    }

    /**
     * search for the gifts, given out by specfic councilor
     * printing the results
     * 
     * @param input - full or partial name
     * @return void
     */
    void searchCouncilorGifts(String input) {
        String validateString = SafeInput.validateStringUserInput(input);
        String query = "SELECT * FROM Councilor inner join Provides on Provides.CouncilorID = Councilor.CouncilorID inner join Gift on Gift.GiftID = Provides.GiftID WHERE CouncilorName LIKE ?;";
        if (validateString == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, "%" + input + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-15s | %-20s | %-20s | %-20s | %-20s", "Councilor ID",
                            "Councilor Name", "Date Gifted", "Reason", "Nature/Value"));
                    System.out.println(String.join("", java.util.Collections.nCopies(130, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-15s | %-20s | %-20s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("CouncilorID")),
                                SafeInput.shrinkString(resultSet.getString("CouncilorName")),
                                SafeInput.shrinkString(resultSet.getString("DateGifted")),
                                SafeInput.shrinkString(resultSet.getString("Reason")),
                                SafeInput.shrinkString(resultSet.getString("NatureValue"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(validateString);
        }
    }

    /**
     * search for WFPS Calls on a given day and the type of incident
     * printing the results
     * 
     * @param inputDate    - mm/dd/yyyy date of the incident
     * @param incidentType - Full or partial incident type
     * @return void
     */
    void searchSpecficWFPSCall(String inputDate, String incidentType) {
        String query = "SELECT * FROM WFPSCALLS WHERE CallTime LIKE ? AND IncidentType LIKE ?";
        String validateOne = SafeInput.validateStringUserInput(inputDate);
        String validateTwo = SafeInput.validateStringUserInput(incidentType);
        if (validateOne == null && validateTwo == null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, inputDate + "%");
                preparedStatement.setString(2, incidentType + "%");

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s | %-20s", "Incident Number",
                            "Incident Type", "Call Time", "MotorVehicleIncident", "Units", "WardID"));
                    System.out.println(String.join("", java.util.Collections.nCopies(150, "-")));

                    while (resultSet.next()) {

                        System.out.println(String.format("%-20s | %-20s | %-30s | %-20s | %-20s | %-20s",
                                SafeInput.shrinkString(resultSet.getString("IncidentNumber")),
                                SafeInput.shrinkString(resultSet.getString("IncidentType")),
                                SafeInput.shrinkString(resultSet.getString("CallTime")),
                                SafeInput.shrinkString(resultSet.getString("MotorVehicleIncident")),
                                SafeInput.shrinkString(resultSet.getString("Units")),
                                SafeInput.shrinkString(resultSet.getString("WardID"))));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error: incorrect input");
        }
    }

    /**
     * Counts the amount of gifts given out by each councilor
     * printing the results
     * 
     * @param none
     * @return void
     */
    void countGiftsPerCouncilor() {

        String query = "SELECT Councilor.CouncilorName, COUNT(Provides.GiftID) AS NumberOfGifts " +
                "FROM Councilor " +
                "LEFT JOIN Provides ON Councilor.CouncilorID = Provides.CouncilorID " +
                "GROUP BY Councilor.CouncilorName";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println(String.format("%-20s | %-20s", "Councilor Name", "Number of Gifts"));
            System.out.println(String.join("", java.util.Collections.nCopies(50, "-")));

            while (resultSet.next()) {
                System.out.println(String.format("%-20s | %-20s",
                        SafeInput.shrinkString(resultSet.getString("CouncilorName")),
                        SafeInput.shrinkString(resultSet.getString("NumberOfGifts"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lists the top wards with the most WFPS Calls
     * 
     * @param input amount of Wards to be returned
     * @return void
     */
    public void listTopWardsWithMostWFPS(String input) {

        int topX = SafeInput.validateIntegerUserInput(input);
        if (topX > 0) {
            String query = "SELECT TOP (?) Ward.WardName, COUNT(WFPSCALLS.IncidentNumber) AS NumberOfCalls " +
                    "FROM Ward " +
                    "LEFT JOIN WFPSCALLS ON Ward.WardID = WFPSCALLS.WardID " +
                    "GROUP BY Ward.WardName " +
                    "ORDER BY NumberOfCalls DESC";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, topX);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    System.out.println(String.format("%-20s | %-20s", "Ward Name", "Number of WFPS Calls"));
                    System.out.println(String.join("", java.util.Collections.nCopies(50, "-")));

                    while (resultSet.next()) {
                        String wardName = SafeInput.shrinkString(resultSet.getString("WardName"));
                        int numberOfCalls = resultSet.getInt("NumberOfCalls");

                        System.out.println(String.format("%-20s | %-20s", wardName, numberOfCalls));
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error: Invalid Input\n");
            }
        } else {
            System.out.println("Error: Invalid Input\n");
        }
    }

    /**
     * Find most common complaint types between Two months in a Ward
     * 
     * @param wardName   name of the ward
     * @param monthBegin beginning month (mm)
     * @param monthEnd   ending month (mm)
     * @return void
     */
    void mostCommonComplaintTypes(String wardName, String monthBegin, String monthEnd) {
        String query = "SELECT TOP 5 ComplainType1, COUNT(RSN) AS ComplaintCount " +
                "FROM ByLaw " +
                "WHERE WardID = (SELECT WardID FROM Ward WHERE WardName = ?) " +
                "AND (MONTH(InDate) BETWEEN ? AND ?) " +
                "GROUP BY ComplainType1 " +
                "ORDER BY ComplaintCount DESC";

        String validateOne = SafeInput.validateStringUserInput(wardName);
        int validateTwo = SafeInput.validateIntegerUserInput(monthBegin);
        int validateThree = SafeInput.validateIntegerUserInput(monthEnd);
        if (validateOne == null) {

            if (validateTwo >= 1 && validateTwo < validateThree && validateThree <= 12) {

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, wardName);

                    preparedStatement.setInt(2, validateTwo);
                    preparedStatement.setInt(3, validateThree);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {

                        System.out.println(String.format("%-30s | %-20s", "Complaint Type", "Complaint Count"));
                        System.out.println(String.join("", java.util.Collections.nCopies(60, "-")));

                        while (resultSet.next()) {
                            String complaintType = SafeInput.shrinkString(resultSet.getString("ComplainType1"));
                            int complaintCount = resultSet.getInt("ComplaintCount");

                            System.out.println(String.format("%-30s | %-20s", complaintType, complaintCount));
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error: Invalid Input");
            }
        } else {
            System.out.println("Error: Invalid Input");
        }
    }

    /**
     * Lists Each ward and each type Of WFPS call incident
     * with a total amount of calls made of each
     * 
     * @param None
     * @return void
     */
    void countWFPSByIncidentTypeAndWard() {
        String query = "SELECT WardName, IncidentType, COUNT(*) AS IncidentCount " +
                "FROM WFPSCALLS " +
                "INNER JOIN Ward on Ward.WardID = WFPSCALLS.WardID " +
                "GROUP BY WardName, IncidentType " +
                "ORDER BY WardName, IncidentCount DESC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println(String.format("%-20s | %-30s | %-20s", "Ward Name", "Incident Type", "Incident Count"));
            System.out.println(String.join("", java.util.Collections.nCopies(70, "-")));

            while (resultSet.next()) {
                String wardID = SafeInput.shrinkString(resultSet.getString("WardName"));
                String incidentType = SafeInput.shrinkString(resultSet.getString("IncidentType"));
                int incidentCount = resultSet.getInt("IncidentCount");

                System.out.println(String.format("%-20s | %-30s | %-20s", wardID, incidentType, incidentCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Count the number of permits by type for each ward
     * printing the results
     * 
     * @param None
     * @return void
     */
    void countPermitsByTypeAndWard() {
        String query = "SELECT WardName, PermitType, COUNT(*) AS PermitCount " +
                "FROM Permits " +
                "INNER JOIN Ward on Ward.WardID = Permits.WardID " +
                "GROUP BY WardName, PermitType " +
                "ORDER BY WardName, PermitCount DESC;";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println(String.format("%-20s | %-30s | %-20s", "Ward Name", "Permit Type", "Permit Count"));
            System.out.println(String.join("", java.util.Collections.nCopies(70, "-")));

            while (resultSet.next()) {
                String wardID = SafeInput.shrinkString(resultSet.getString("WardName"));
                String permitType = SafeInput.shrinkString(resultSet.getString("PermitType"));
                int permitCount = resultSet.getInt("PermitCount");

                System.out.println(String.format("%-20s | %-30s | %-20s", wardID, permitType, permitCount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes all the entries from the database
     * 
     * @param None
     * @return void
     */
    void deleteAll() {

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Error: Cannot delete Database");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("sql/DeleteCommand.txt"));
                Statement statement = connection.createStatement()) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    statement.execute(line);
                }
            }
            connection.commit();
            System.out.println("Database Deleted Successfully");
        } catch (Exception e) {
            try {
                connection.rollback();
                System.out.println("Database Deleted Failed");
            } catch (SQLException ex) {
                System.err.println("Error: Please Contact System Administrator " + ex.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error: Cannot delete Database");
            }
        }
    }

    /**
     * Enters all the information into the database
     * 
     * @param None
     * @return void
     */
    void populateAll() {

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Error: Cannot delete Database");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("sql/PopulateCommand.txt"));
                Statement statement = connection.createStatement()) {

            String line;
            System.out.println("this may take a while...");
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    statement.execute(line);
                }
            }
            connection.commit();
            System.out.println("Database populated Successfully");
        } catch (Exception e) {
            try {
                connection.rollback();
                System.out.println("Database Deleted Failed");
            } catch (SQLException ex) {
                System.err.println("Error: Please Contact System Administrator " + ex.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Error: Cannot delete Database");
            }
        }
    }
}
