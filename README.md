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
  * ```google-play-cli --help``` - list of all commands
  * ```google-play-cli apk list --config service_account.json --package-name <your uploaded apk package name>```
  
* Apk info extraction
  
  Install [Apk info extractor](https://github.com/Vacxe/apk-info-extractor) and JQ `apkinfoextractor <pathtoApk> | jq '.package'`
  

License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
