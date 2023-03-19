## Commits and merge requests
Commits should include the issue number, if applicable, as well as a description of what has been added/changed.

Add co-author(s) if multiple have been working on the same issue. Format: `Co-authored by: NAME <NAME@EXAMPLE.COM>`

Merge requests should be related to specific issues, and should not be merged without having someone else reviewed.

## Common errors when first pulling

### Error SDK location not found

For this error, create a file in the root of the project called `local.properties`, and copy the following:

```
## This file must *NOT* be checked into Version Control Systems,
# as it contains information specific to your local configuration.
#
# Location of the SDK. This is only used by Gradle.
# For customization when using a Version Control System, please read the
# header note.
#Thu Mar 09 08:46:01 CET 2023
sdk.dir=[ENTER SDK LOCATION HERE]
```

Copy the path to your android SDK and paste it on `sdk.dir=`.

On windows, one can quickly find the path by pressing Windows+R -> write `%appdata%`, move up a directory -> then /Local/Android/SDK

On mac, ¯\\_(ツ)_/¯

### Installed Build Tools Revision 31.0.0 is corrupted
For this error: 

    1. Go to `C:\Users\user\AppData\Local\Android\Sdk\build-tools\31.0.0` on windows, `~/Library/Android/sdk/build-tools/31.0.0` on mac (?)
    2. Rename d8.bat to dx.bat
    3. Go to `.../Android\Sdk\build-tools\31.0.0/lib`
    4. Rename d8.jar to dx.jar
How does this even happen?

## Other

### Do not update the gradle plugin, as that seem to ruin things, which is kind of f%¤!ed
