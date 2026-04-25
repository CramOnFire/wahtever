# Tales of Triumph and Tragedy

## How to run:
1. Clone the repository
2. Run the following commands in the terminal (compile Java files and run `Main`):
```Shell
powershell -ExecutionPolicy Bypass -File .\SimpleRPG\.vscode\build.ps1
java -cp .\SimpleRPG\bin com.simplerpg.Main
```

Note: There is an `addGold` toggle in `GameEngine.java` for testing purposes.

Old how to run
```Shell
cd ~/newwok/SimpleRPG/src
javac -d . com/simplerpg/Main.java com/simplerpg/**/*.java com/simplerpg/*.java
java com.simplerpg.Main
```
