## How to generate and view the Allure report

### Option 1: open the report in a browser right away

If you have the Allure CLI installed, run:

```bash
allure serve target/allure-results
```

This command generates the report in a temporary directory and opens it in your browser.

### Option 2: generate a static report directory

```bash
allure generate target/allure-results -o target/allure-report --clean
```

Then open `target/allure-report/index.html` in your browser.

## Installing the Allure CLI

Allure CLI is required only when you want to generate or serve reports locally.

### Windows

#### Option 1: Chocolatey

If you use Chocolatey, install Allure with:

```powershell
choco install allure
```

#### Option 2: Scoop

If you use Scoop, install Allure with:

```powershell
scoop install allure
```

#### Option 3: manual install

1. Download the latest Allure archive for Windows from the official releases page:
    - https://github.com/allure-framework/allure2/releases
2. Unzip the archive to a folder, for example:
    - `C:\Tools\allure`
3. Add the `bin` folder to your `PATH` environment variable, for example:
    - `C:\Tools\allure\bin`
4. Open a new terminal and verify the installation:

```powershell
allure --version
```

### macOS

If Allure is not installed yet on macOS, the easiest option is usually Homebrew:

```bash
brew install allure
```

### Linux

On Linux, you can usually use your package manager if Allure is available, or install it manually from the official release archive and add the `bin` folder to your `PATH`.

To verify the installation on any platform:

```bash
allure --version
```
