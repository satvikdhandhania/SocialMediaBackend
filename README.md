Start the MongoDB Service: You can start MongoDB as a macOS service. This means it will automatically start when you boot your Mac.
- brew services start mongodb-community

If you prefer to run it manually in the foreground (useful for seeing log output directly in the terminal, but you'll need to keep that terminal window open):
- mongod --config /usr/local/etc/mongod.conf --fork --logpath /usr/local/var/log/mongodb/mongo.log

Connect with MongoDB Shell (mongosh): Open a new Terminal window and type:
- mongosh

If MongoDB is running correctly, you'll be connected to your local server, and you'll see a prompt like test>. This indicates you're connected to the default test database. You can type exit or press Ctrl+D to leave the shell.
By default, MongoDB listens on port **27017** and stores its data in **/usr/local/var/mongodb** (on newer macOS versions with Homebrew) or /data/db (older default, but Homebrew manages this for you).

To Stop the MongoDB Service (if started with brew services):
- brew services stop mongodb-community


Run Your Spring Boot Application:
From your IDE: Right-click the main application class and select "Run".
Using Maven: 
- ./mvnw spring-boot:run


POST Request or use Postman
- curl -X POST -H "Content-Type: application/json" -d '{"name": "My First Item", "description": "This is a test item."}' http://localhost:8080/api/items


For debugging you can run using maven run config with the following command. Otherwise the breakpoints wont be hit during debug. Make sure to click on attach debgger on the console

clean spring-boot:run -Dspring-boot.run.jvmArguments=-Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005
