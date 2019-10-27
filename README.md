# TODO: Insert Awesome Title Here :))))



## Description



## Dev Stuff
This section includes a short guide how to get running with this project.

### Server bits

Pre-requisites:
- Glassfish v4.1.2 (or, probably, Payara)
- Java 8 (maximum)
  - Note that the presence of Java 9 or higher may have compatability issues with Glassfish/Payara 
- MySQL/MariaDB database


Environment Variables
- `BT_DB_USER` - the BudgetTime database username
- `BT_DB_PASSWORD` - the BudgetTime database password
- `BT_DB_URI` - the BudgetTime connection string (e.g. `jdbc:mysql://<host>:<port>/<dbname>`)




## Troubleshooting

### No suitable driver found for JDBC MySQL
Ensure that all artifacts have been added -- this occurs when the MySQL driver hasn't been included in the class path

### Keystore file cannot be created
This happens when the driver attempts to connect via SSL -- set use ssl to false (often defaults to REQUIRED) 



