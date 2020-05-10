# Google play CLI
CLI tool for publish and management application in Google Play Console.

Actually this library is CLI wrapper for [Google Play Java library](https://developers.google.com/android-publisher/api-ref)

List of all vailable commands in [wiki](https://github.com/Vacxe/google-play-cli/wiki/Google-Play-CLI) *WIP*

### How to install
#### Brew

```
brew tap vacxe/tap
brew install vacxe/tap/googleplaycli
```

### How to use
* Before at all you should obtain a `serviceAccount.json`

  Goto: Google Play Console -> Developer Account -> Api access -> Service Accounts -> CREATE SERVICE ACCOUNT
  Follow the instruction and grant the access for service accout
* Try simplest command

  `google-play-cli apk list --config service_account.json --package-name <your uploaded apk package name>`
  
* Apk info extraction
  
  Install [Apk info extractor](https://github.com/Vacxe/apk-info-extractor) and JQ `apkinfoextractor <pathtoApk> | jq '.package'`
