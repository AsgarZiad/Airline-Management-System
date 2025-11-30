@echo off
REM Create Desktop Shortcut for Airline Management System

setlocal enabledelayedexpansion

set "projectPath=d:\PAIN\STUDY STUFF\OOP Project\AirlineManagementSystem\AirlineManagementSystem\Airline-Management-System"
set "batchFile=!projectPath!\RUN.bat"
set "desktopPath=%USERPROFILE%\Desktop"
set "shortcutFile=!desktopPath!\Airline Management System.lnk"

REM Create shortcut using PowerShell
powershell -Command ^
  "$WshShell = New-Object -ComObject WScript.Shell; " ^
  "$shortcut = $WshShell.CreateShortcut('[!shortcutFile!]'); " ^
  "$shortcut.TargetPath = '[!batchFile!]'; " ^
  "$shortcut.WorkingDirectory = '[!projectPath!]'; " ^
  "$shortcut.Description = 'Launch Airline Management System'; " ^
  "$shortcut.Save()"

if errorlevel 1 (
    echo Failed to create shortcut
    pause
    exit /b 1
)

echo Shortcut created successfully at: !shortcutFile!
echo You can now click on it from your Desktop to run the application
pause
