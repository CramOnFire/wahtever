# Tales of Triumph and Tragedy

![](https://i.ibb.co/DPbwDvgM/TTT-cropped.avif)

## How to run:
1. Clone the repository
2. Run the following commands in the terminal (compile Java files and run `Main`):

Windows
```Shell
powershell -ExecutionPolicy Bypass -File .\TTT\.vscode\build.ps1
java -cp .\TTT\bin com.ttt.Main
```

Linux
```Shell
cd ~/newwok/TTT/src
javac -d . com/ttt/Main.java com/ttt/**/*.java com/ttt/*.java
java com.ttt.Main
```

Note: There is an `addGold` toggle in `GameEngine.java` for testing purposes.
