[//]: # (Please document your code :&#41;)

# Reporting Service

##Report 
### - id
### - ReporterID (user ID)
### - ReportedID (user ID)
### - Reason


##Possible Requests

###Get report 
#### Url: /api/v1/reports/get/{reportID}
#### Response: "Return the required report if exists."
#### Description: "Gets the report with the given report ID"

###Add report
#### Url: /api/v1/reports/get/{reportID}
#### JSON body sample: { ReporterID: "123" , ReportedID:"456" , reason:"Test"}
#### Response: "Return the ID of the new report"
#### Description: "Add a new report with the given data"


