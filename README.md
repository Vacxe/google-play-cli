# Google play CLI
Transparent CLI tooling for managing Google Play Console.

## Table of Contents

- [Introduction](#introduction)
- [Installation](#installation)
  - [Homebrew](#homebrew)
  - [Docker](#docker)
  - [GitHub Actions](#github-action)
- [How to use CLI](#how-to-use-cli-directly)

This library is transparent CLI wrapper for official [Google Play Java Library](https://developers.google.com/android-publisher/api-ref)

## Introduction

Previous libs what been used for application deployment contained disadvantages what blocked me from application delivery. As well, all libraries contains internal logic for uploading, but will be nice hot have some more **transparent** solution.
* [Gradle Triple-T Plugin](https://github.com/Triple-T/gradle-play-publisher)
  * Integration with build system - Distribution should not be a part of build system. Its blocking us to create a reusable chains on CI. If project is huge, it will create a lot of overhead with evaluation project.
  * Not flexible enough - suitable only for Apk/Bundle upload
* [r0adkll/sign-android-release](https://github.com/r0adkll/sign-android-release)
  * Not flexible enough - suitable only for Apk/Bundle upload

**Advantages of this project**
  * Core: Transparent CLI wrapper for Official Google Play Console library
  * Can be installed on host directly as CLI (via brew or binaries) or can be used via Docker or GitHub actions. You are able to test it locally before deployment.
  * Docker: Transparent environment for CLI, as well JQ for easier Json response management in case of custom actions
  * GitHub Action: Transparent wrapper for Docker, with amount of custom *templates* to simplify day to day actions like upload apk

## Installation

### Homebrew

```
brew tap vacxe/tap
brew install vacxe/tap/googleplaycli
```

### [Docker](https://github.com/Vacxe/google-play-cli-kt/pkgs/container/google-play-cli)

```
ghcr.io/vacxe/google-play-cli:<VERSION>
```

### [GitHub Action](https://github.com/marketplace/actions/google-play-console)

[GitHub Action](https://github.com/marketplace/actions/google-play-console) `vacxe/google-play-cli-kt` is transparent wrapper over [Docker](https://github.com/Vacxe/google-play-cli-kt/pkgs/container/google-play-cli) `google-play-cli-kt`, what contains transparent CLI warapper for [Google Play Java Library](https://developers.google.com/android-publisher/api-ref). It can provide for you possibility to write any custom scripts in depends on you needs or use one of **[Available templates](github-action/README.md)**.

*Example of APK uploading template:*
```yaml
- uses: vacxe/google-play-cli-kt@master
  with:
    template: apk-upload
    service-account-json: ${{ secrets.SERVICE_ACCOUNT_JSON }}
    version-code: ${{ github.run_number }}
    package-name: <package name>
    path-to-apk: <path to apk>
    track: internal
    status: draft
```

## How to use CLI directly
* Before you started you should obtain a `serviceAccount.json`

  Goto: Google Play Console -> Developer Account -> Api access -> Service Accounts -> CREATE SERVICE ACCOUNT
  Follow the instruction and grant the access for service account
* Try basic command
  * ```google-play-cli --help``` - list of all commands
  * ```google-play-cli apk list --config-file service_account.json --package-name <your uploaded apk package name>```

Example how to upload apk with step by step explanation:

* Let configure few environment variables first, what will make our next CLI calls much easier
```shell
# If proxy needed
#PLAYSTORE_PROXY=192.168.0.1:3128

# Increase connection timeout
#PLAYSTORE_CONNECTION_TIMEOUT=PT6M

export PLAYSTORE_SERVICE_ACCOUNT_JSON_FILE="/path/to/service_account.json"
# Or set a plain text
#export PLAYSTORE_SERVICE_ACCOUNT_JSON_CONTENT="CONTENT OF JSON"
export APP_PACKAGE_NAME="com.my.package"
```

Exporting is not required, but if you will not decide to do it you may need to add for each command
* `--config-file "/path/to/service_account.json"`
  * or `--config-content "CONTENT OF JSON"`
* `--package-name "com.my.package"`

*Tip: On CI you can add service account json file as a secret to environment variable*

---

* First of all you may need to create **edit_id**
```shell
edit_id=$(google-play-cli edit create)
```
* With existing `edit_id` you can do any modifications in Play Console, let's upload APK
```shell
google-play-cli apk upload --edit-id $edit_id --apk "path/to/my.apk"
```
* APK been uploaded, we need to assign it to `track`. Let choice `internal` track and status will be `draft`, as well we need to know version code (for example `42`)
```shell
google-play-cli tracks update --edit-id $edit_id --track "internal" --status "draft" --version-code "42"
```
* As a non-required step you can validate your edit at any time
```shell
google-play-cli edit validate --edit-id $edit_id
```
* And as last step - commit your changes
```shell
google-play-cli edit commit --edit-id $edit_id
```

## License

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
