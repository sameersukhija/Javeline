#region --- Au3Recorder generated code Start (v3.3.9.5 KeyboardLayout=00000409)  ---

#region --- Internal functions Au3Recorder Start ---
Func _Au3RecordSetup()
Opt('WinWaitDelay',100)
Opt('WinDetectHiddenText',1)
Opt('MouseCoordMode',0)
Local $aResult = DllCall('User32.dll', 'int', 'GetKeyboardLayoutNameW', 'wstr', '')
If $aResult[1] <> '00000409' Then
  MsgBox(64, 'Warning', 'Recording has been done under a different Keyboard layout' & @CRLF & '(00000409->' & $aResult[1] & ')')
EndIf

EndFunc

Func _WinWaitActivate($title,$text,$timeout=0)
	WinWait($title,$text,$timeout)
	If Not WinActive($title,$text) Then WinActivate($title,$text)
	WinWaitActive($title,$text,$timeout)
EndFunc

_AU3RecordSetup()
#endregion --- Internal functions Au3Recorder End ---

_WinWaitActivate("File Upload","Namespace Tree Contr")

@HomeDrive=Send(@HomePath)+Send("\EFOGC-3577-updated-Testcases\src\test\resources\input\catalogmanager\Offers\VoucherCodes.csv")

sleep (3500)

Send ("{TAB}")

Send ("{TAB}")

Send ("{ENTER}")

Send ("{ENTER}")
_WinWaitActivate("File Upload","Namespace Tree Contr")
MouseClick("left",1152,629,1)

Exit
#endregion --- Au3Recorder generated code End ---
