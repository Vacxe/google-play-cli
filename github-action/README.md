# Google play CLI (GitHub Actions)

## Requirements
Any GitHub Action `vacxe/google-play-cli-kt` required two parameters to be set
  * `service-account-json` - Plain text Service Account JSON, can be stored and provided via `secrets` 
  
    *Example: `service-account-json: ${{ secrets.SERVICE_ACCOUNT_JSON }}`*
  * `package-name` - package name for target application

    *Example: `com.my.application`*

## Custom Script
[GitHub Action](https://github.com/marketplace/actions/google-play-console) `vacxe/google-play-cli-kt` is transparent wrapper over [Docker](https://github.com/Vacxe/google-play-cli-kt/pkgs/container/google-play-cli) `google-play-cli-kt`, what contains transparent CLI warapper for [Google Play Java Library](https://developers.google.com/android-publisher/api-ref). It can provide for you possibility to write any custom scripts in depends on you needs.

*Example: Request all bundles*

```yaml
      - uses: vacxe/google-play-cli-kt@master
        with:
          service-account-json: ${{ secrets.SERVICE_ACCOUNT_JSON }}
          package-name: my.package.name
          custom-script: 'google-play-cli bundles list | jq'
```

However, most of the cases is actually repeatable for many projects. You can take a look on *Templates* or **contribute** to it.

## Templates

- [Apk](#apk)
  - [Upload](#upload)
- [Bundles](#bundles)
  - [Upload](#upload-1)
- [Deobfuscation Files](#deobfuscation-files)
  - [Upload](#upload-2)

*Note: replace `@master` with latest version for stable behaviour or leave it for last updates*

## Apk
### Upload
* `path-to-apk` - Path to APK file
* `version-code` - Integer version code of Apk
* `track` - Target track
* `changes-not-sent-for-review` - Do not send changes for review
* `status` - The status of a release
* `user-fraction` - Fraction of users who are eligible to receive the release. 0 < fraction < 1. To be set, release status must be "inProgress" or "halted".

*Example:*
```yaml
- uses: vacxe/google-play-cli-kt@master
  with:
    template: apk-upload
    service-account-json: ${{ secrets.SERVICE_ACCOUNT_JSON }}
    package-name: <package name>    
    version-code: ${{ github.run_number }}
    path-to-apk: <path to apk>
    track: internal
    status: draft
```

## Bundles
### Upload
* `path-to-bundle` - Path to Bundle file
* `version-code` - Integer version code of Bundle
* `track` - Target track
* `changes-not-sent-for-review` - Do not send changes for review
* `status` - The status of a release
* `user-fraction` - Fraction of users who are eligible to receive the release. 0 < fraction < 1. To be set, release status must be "inProgress" or "halted".

*Example:*
```yaml
- uses: vacxe/google-play-cli-kt@master
  with:
    template: bundles-upload
    service-account-json: ${{ secrets.SERVICE_ACCOUNT_JSON }}
    package-name: <package name>
    version-code: ${{ github.run_number }}
    path-to-bundle: <path to bundle>
    track: internal
    status: draft
```

## Deobfuscation Files
### Upload
* `path-to-mapping` - Path to Mapping file
* `version-code` - Integer version code of assigned Apk or Bundle
* `changes-not-sent-for-review` - Do not send changes for review

*Example:*
```yaml
- uses: vacxe/google-play-cli-kt@master
  with:
    template: deobfuscation-files-upload
    service-account-json: ${{ secrets.SERVICE_ACCOUNT_JSON }}
    package-name: <package name>
    path-to-mapping: <path to mapping file>
    version-code: ${{ github.run_number }}
```