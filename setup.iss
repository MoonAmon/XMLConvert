; Inno Setup Script
[Setup]
AppName=XMLConverter
AppVersion=1.0
DefaultDirName={pf}\XMLConverter
DefaultGroupName=XMLConverter
OutputBaseFilename=XMLConverterSetup
Compression=lzma
SolidCompression=yes

[Files]
Source: "C:\Users\USER\Desktop\xmlconvert.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\Users\USER\Downloads\mimetypes_gnome_mime_text_xml_15656.ico"; DestDir: "{app}"; Flags: ignoreversion

[Icons]
Name: "{group}\XMLConverter"; Filename: "{app}\xmlconvert.exe"; IconFilename: "{app}\your-icon.ico"

[Run]
Filename: "{app}\xmlconvert.exe"; Description: "Launch XMLConverter"; Flags: nowait postinstall skipifsilent