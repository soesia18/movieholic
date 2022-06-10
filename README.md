# movieholic

This project was tested with IntelliJ IDEA 2022.1.2 (Ultimate Edition)<br>
and executed on a GlassFish 6.2.5 Server (JDK 17)

### Modified GlassFish:<br>
>Download the .jar File from my OneDrive: https://htlkaindorfat-my.sharepoint.com/:u:/g/personal/soesia18_htl-kaindorf_at/EW7gsBtyo_hJjX2uqFKx3vgBrjesdwZsey3rrrIv5TxsHA?e=30JkgW <br>
Copy the .jar in your Folder: glassfish6>glassfish>lib <br>
> 

>Go to you your glassfish6>bin and double-click on asadmin.bat <br>
start your domain1 with the command "start-domain domain1" <br>
> 

>Open any browser and go to the Link http://localhost:4848/ this should be an admin console <br>
On the left side under Resources there is the point JDBC go now to the JDBC Connection Pools <br>
Press the new button:<br>
>

>pool name: any you want (mysql);<br>
Resource Type: java.sql.Driver;<br>
Database Driver Vendor: MySql<br>
>

> Press Next (top right corner)<br>
Driver Classname: com.mysql.cj.jdbc.MysqlDataSource<br>
> 


> Additional Properties: <br>
password: MovieholicSecret187!<br>
user: mh<br>
URL: jdbc:mysql://movieholic.ddns.net/movieholic?useTimeZone=true&serverTimezone=CET&autoReconnect=true&useSSL=false<br>
> 

>Press Finish
> 