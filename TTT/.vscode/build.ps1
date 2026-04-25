param()
Set-StrictMode -Version Latest
$root = Split-Path -Parent $MyInvocation.MyCommand.Definition
$srcDir = Join-Path $root "..\src"
$binDir = Join-Path $root "..\bin"
if (-not (Test-Path $binDir)) { New-Item -ItemType Directory -Path $binDir | Out-Null }
$sourcesFile = Join-Path $root "sources.txt"
Get-ChildItem -Path $srcDir -Recurse -Filter "*.java" | Select-Object -ExpandProperty FullName | Set-Content -Path $sourcesFile -Encoding ascii
$javacArg = "@" + $sourcesFile
Write-Output "Compiling Java sources into: $binDir"
& javac -d $binDir $javacArg 2>&1 | Write-Output
if ($LASTEXITCODE -ne 0) { Write-Error "javac exited with code $LASTEXITCODE"; exit $LASTEXITCODE }
Remove-Item $sourcesFile -ErrorAction SilentlyContinue
