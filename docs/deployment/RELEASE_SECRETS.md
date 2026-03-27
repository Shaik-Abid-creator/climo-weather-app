# Release CI Secrets

Configure the following GitHub repository secrets before running `.github/workflows/android-release.yml`:

- `RELEASE_KEYSTORE_B64`: Base64-encoded JKS keystore file.
- `RELEASE_STORE_PASSWORD`: Keystore password.
- `RELEASE_KEY_ALIAS`: Key alias inside the keystore.
- `RELEASE_KEY_PASSWORD`: Key password.

## Create Base64 value

```bash
base64 -w 0 release-keystore.jks
```

Use the output as the value for `RELEASE_KEYSTORE_B64`.
