sudo su
#install dependencies

#mysql
apt-get install mysql-server
mysql -u root -p
CREATE DATABASE toDoListBD;
quit 

#nodejs
curl -sL https://deb.nodesource.com/setup_10.x | sudo -E bash -
apt-get install nodejs npm

#github project
apt-get install git
git clone https://github.com/Nebulosa-R/ToDo-List.git
cd ToDo-List 

#run server
cd server
./mvnw spring-boot:run

cd ..
#run client
cd client
npm start

