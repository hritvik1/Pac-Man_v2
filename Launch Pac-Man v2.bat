:: Written By ~Hritvik~
@echo off
set PROGRAM = Pac-Man v2
if not "%1" == "max" start /MAX cmd /c %0 max & exit/b
echo Compiling Pac-Man v2 By Hritvik.....
javac PacMan_v2.java
timeout /t 3 /nobreak>nul
echo Game Compiled Successfully..
echo Creating Directories For Save Game Files....
md C:\Games\Pac-Man_v2\Saves
md C:\Games\Pac-Man_v2\User_Saves
timeout /t 2 /nobreak>nul
echo Directories Created Successfully!!!
echo Launching Game In.....
timeout /t 5 /nobreak
java PacMan_v2
pause