from time import sleep
from tqdm import tqdm
import csv


class scrape:

    # constructor
    def __init__(self,fileName,colArray,outputFilename):
        self.fileName = fileName
        self.colArray = colArray
        self.dataArray = []
        self.lineCount = self.count_lines()
        self.outputFile =  outputFilename
        self.LINELIMIT = 5000
        


    # built sql code as a string
    def sqlStringBuilder(self,command,tableName):
        if(command == "INSERT"):
            command = f"INSERT INTO {tableName}({self.colArray[0]}"

            i = 1;

            while(i < len(self.colArray)):
                command += ", " +self.colArray[i]
                i +=1
            command += ") VALUES \n"
            self.scrapeData()

            command += self.dataArray[0] +""

            for i in self.dataArray[1:]:
                stringBuilder = ",\n" + i
                command += stringBuilder +""

            command += ";"
            self.writeToFile(command)
        return command
    

    def try_parse_int(self,value):
        try:
            # Attempt to convert the value to an integer
            return str(int(value))
        except ValueError:

            return "'"+ value + "'"

    # wrote the sql code to a text file
    def writeToFile(self,command):
        with open(self.outputFile, 'w') as file:
            file.writelines(command)


    # formatting to get a useless progress bar to work
    def clear_line(self, length=200):
        print('\r' + ' ' * length, end='\r', flush=True)

    # did the actually scraping by reading the csv file
    def scrapeData(self):
        colIndices = []

        print("Starting Data Scraping.... ")
        with open(self.fileName, 'r') as dataFile:
            headerLine = dataFile.readline().strip().split(",")
            reader = csv.reader(dataFile)
            
            for _ in self.colArray:
                colIndices.append(headerLine.index(_))


            pbar = tqdm(total=self.lineCount, unit='lines')
            count = 0

            floor = self.lineCount // self.LINELIMIT


            for i in reader:
                if ( floor == 0 or count  % floor == 0 ):   # assures we only take at most ~5k entires and and to take then evenly through out the dataset
                    currLine = i
                    stringBuilder = f"( {self.try_parse_int(currLine[colIndices[0]])} "


                    for j in colIndices[1:]:
                        stringBuilder += "," + self.try_parse_int(currLine[j] +"")
                
                    stringBuilder += ")"
                    self.dataArray.append(stringBuilder)

                    # fomrating string for terminal 
                    if len(stringBuilder) > 75:
                        displayData = (stringBuilder[:75] + '...') 
                    else:
                        displayData = stringBuilder

                    pbar.set_description(f"Processing {displayData}")
                pbar.update(1)                      
                count += 1  
            pbar.close()
            print("\nData Scraping Finsihed")

    #count lines for progress bar
    def count_lines(self):
        with open(self.fileName, 'r') as file:
            line_count = sum(1 for line in file)
        return line_count




#this got our data to about 99% formatted as we needed
# however we decided to just maually change the column names instead of writing code
#to handle cases with spaces in the column name


    


def main():


    # -----------------------------------------------------------
    # to use first is just a list of each column name you want from the text fle
    # create scrape object with data file path, list above and pick an output list name
    # call sqlStringbuilder with "INSERT" command and the desired table name
    # -----------------------------------------------------------

    #we then created the relationships using SQL and edited anything we needed to by hand

    wfpsCols = ["Incident Number","Incident Type","Call Time","Motor Vehicle Incident","Units","Ward"]
    wfpsScrape = scrape("Data/WFPS_Call_Logs_20240215.csv",wfpsCols, "Scraped/WFPSData.txt")
    wfpsScrape.sqlStringBuilder("INSERT","WFPSCALLS")

    pubNot = ["Notice Type","Notice ID","Address","Order Date","Ward"]
    pubNotScrape = scrape("Data/Public_Notices_20240215.csv",pubNot,"Scraped/PublicNoticesData.txt")
    pubNotScrape.sqlStringBuilder("INSERT","publicNotices")

    giftCols = ["Id","Date Gifted","Nature / Value","Reason","Council Member"]
    giftScrape = scrape("Data/Gift_Registry_20240215.csv",giftCols,"Scraped/GiftData.txt")
    giftScrape.sqlStringBuilder("INSERT","gifts")

    permitCol = ["Permit Number","Issue Date","Permit Type","Permit Group","Sub Type","Work Type","Street Number","Street Name","Street Type","Ward"]
    permitScrape = scrape("Data/Detailed_Building_Permit_Data_20240215.csv",permitCol,"Scraped/PermitData.txt")
    permitScrape.sqlStringBuilder("INSERT", "permits")

    byLawCol = ["Folder RSN","NBHD Number","Complaint Type 1","Indate","Ward"]
    byLawScrape = scrape("Data/By-Law_Investigations_20240215.csv",byLawCol,"Scraped/byLawData.txt")
    byLawScrape.sqlStringBuilder("INSERT","byLaw")


    councilorCol = ["Councillor","Number","Name"]
    councilorScrape = scrape("Data/electoral_ward.csv", councilorCol,"Scraped/councilorData.txt")
    councilorScrape.sqlStringBuilder("INSERT","councilor")

    electionCol = ["Date","Area","Position"]
    electionScrape = scrape("Data/Winnipeg_Election_Results_20240215.csv",electionCol,"electionData.txt")
    electionScrape.sqlStringBuilder("INSERT","generalElection")

    
    candidateCol = ["Candidate","Won","Votes","Date","Area"]
    candidateScrape = scrape("Data/Winnipeg_Election_Results_20240215.csv",candidateCol,"candidateData.txt")
    candidateScrape.sqlStringBuilder("INSERT","candidate")

main()
    
