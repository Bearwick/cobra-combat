### Common errors when first pulling

#### Error SDK location not found
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
On mac, ¯\_(ツ)_/¯

### Other

#### Do not update the gradle plugin.