use cs3380

--clean up
drop table if exists Provides;
drop table if exists Becomes;
drop table if exists NominatedFor;
drop table if exists Investigate;
drop table if exists Gift;
drop table if exists Councilor;
drop table if exists Candidate;
drop table if exists GeneralElection;
drop table if exists WFPSCALLS;
drop table if exists PublicNotices;
drop table if exists Permits;
drop table if exists ByLaw;
drop table if exists Ward;

create table Gift(
    GiftID integer primary key,
    DateGifted VARCHAR(MAX) not null,
    NatureValue VARCHAR(MAX) not null,
    Reason VARCHAR(MAX) not null
);

create table Ward(
    WardID integer primary key,
    WardName VARCHAR(MAX) not null
);

create table Councilor(
    CouncilorID integer primary key IDENTITY(1,1),
    CouncilorName VARCHAR(MAX) not null,
    WardID integer references Ward(WardID)
);

create table Provides(
    ProvidesID integer primary key IDENTITY(1,1),
    GiftID integer references Gift(GiftID),
    CouncilorID integer references Councilor(CouncilorID),
);

create table Candidate(
	CandidateID integer primary key IDENTITY(1,1),
	CandidateName VARCHAR(MAX) not null,
	Won VARCHAR(MAX) not null,
	Votes integer,
	CandDate VARCHAR(MAX) not null
);

create table Becomes(
    BecomesID integer primary key IDENTITY(1,1),
	CouncilorID integer references Councilor(CouncilorID),
	CandidateID integer references Candidate(CandidateID),
);

create table GeneralElection(
    ElectionID integer primary key IDENTITY(1,1),
    ElectionDate VARCHAR(MAX) not null,
    WardID integer references Ward(WardID), 
    Position VARCHAR(MAX) not null,
);

create table NominatedFor(
    NominatedForID integer primary key IDENTITY(1,1),
    CandidateID integer references Candidate(CandidateID),
    ElectionID integer references GeneralElection(ElectionID),
);

create table ByLaw(
	RSN integer primary key,
	NBHDName VARCHAR(MAX) not null,
	ComplainType1 VARCHAR(MAX) not null,
	InDate VARCHAR(MAX) not null,
    WardID integer references Ward(WardID)
);

create table Investigate(
    InvestigateID integer primary key IDENTITY(1,1),
    WardID integer references Ward(WardID), 
    RSN integer references ByLaw(RSN),
);

create table WFPSCALLS(
    IncidentNumber integer primary key,
    IncidentType VARCHAR(MAX) not null,
    CallTime VARCHAR(MAX) not null,
    MotorVehicleIncident VARCHAR(MAX) not null,
    Units VARCHAR(MAX) not null,
    WardID integer references Ward(WardID)
);

create table Permits(
	PermitID integer primary key IDENTITY(1,1),
    PermitNumber VARCHAR(MAX) not null,
    IssueDate VARCHAR(MAX) not null,
    PermitType VARCHAR(MAX) not null,
    PermitGroup VARCHAR(MAX) not null,
    SubType VARCHAR(MAX) not null,
    WorkType VARCHAR(MAX) not null,
    StreetNumber integer not null,
    StreetName VARCHAR(MAX) not null,
    StreetType VARCHAR(MAX) not null,
    WardID integer references Ward(WardID)
);

create table PublicNotices(
    NoticeID integer primary key IDENTITY(1,1),
    NoticeType VARCHAR(MAX) not null,
    NoticeNum VARCHAR(MAX) not null,
    NoticeAddress VARCHAR(MAX) not null,
    OrderDate VARCHAR(MAX) not null,
    WardID integer references Ward(WardID)
);
