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
  * ```google-play-cli apk list --config-file service_account.json --package-name <your uploaded apk package name>```
  
* Apk info extraction
  
  Install [Apk info extractor](https://github.com/Vacxe/apk-info-extractor) and JQ `apkinfoextractor <pathtoApk> | jq '.package'`
  
* How to upload apk
Bash function can be copied to any .sh file and used as `uploadapk "path/to/my.apk" "path/to/service_account.json"`

```
function uploadapk(){
  path_to_apk=$1
  export PLAYSTORE_SERVICE_ACCOUNT_JSON_FILE=$2
  
  #If proxy needed
  #PLAYSTORE_PROXY=192.168.0.1:3128
  
  #Increase connection timeout
  #PLAYSTORE_CONNECTION_TIMEOUT=PT6M

  apk_package=$(apkinfoextractor $path_to_apk | jq '.package')
  export APP_PACKAGE_NAME=$apk_package

  apk_version_code=$(apkinfoextractor $path_to_apk | jq '.versionCode')

  edit_id=$(google-play-cli edit create)
  google-play-cli apk upload --edit-id $edit_id --apk $path_to_apk
  google-play-cli tracks update --edit-id $edit_id --track "internal" --apk-version-code $apk_version_code
  google-play-cli edit validate --edit-id $edit_id
  google-play-cli edit commit --edit-id $edit_id

  unset APP_PACKAGE_NAME
  unset PLAYSTORE_SERVICE_ACCOUNT_JSON_FILE
}
```  

On CI you can add service account json file as a secret to environment variable

``
PLAYSTORE_SERVICE_ACCOUNT_JSON_CONTENT
``



### Run in [Docker](https://github.com/Vacxe/google-play-cli-kt/pkgs/container/google-play-cli)

```
ghcr.io/vacxe/google-play-cli:<VERSION>
```


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
