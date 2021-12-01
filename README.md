# Batch Scripts Support
[![Build](https://github.com/aefimov/idea-batch/actions/workflows/build.yml/badge.svg)](https://github.com/aefimov/idea-batch/actions/workflows/build.yml)
[![JetBrains IntelliJ Plugins](https://img.shields.io/jetbrains/plugin/v/265-batch-scripts-support)](https://plugins.jetbrains.com/plugin/265-batch-scripts-support)
[![JetBrains IntelliJ plugins](https://img.shields.io/jetbrains/plugin/d/265-batch-scripts-support)](https://plugins.jetbrains.com/plugin/265-batch-scripts-support)
![GitHub](https://img.shields.io/github/license/aefimov/idea-batch)

Windows Batch Scripts support for IntelliJ IDEA. Supports syntax highlighting, run configurations that can run any command, also empty configurations. Empty run configurations are useful when you want to execute an external tool through a run configuration.

## Changelog
### Changes in version 1.0.13:
* Commenter and file type registration fixes
* Fix error in 2021.3
* Minimum version 2020.1 (201.6668.121)

### Changes in version 1.0.12:
* Decrease minimum version to support older IDE Versions

### Changes in version 1.0.11:
* Unset the maximum intellij version to not required rebuild (by wibotwi)
* Allow installation on all JetBrains IDEs (by wibotwi)

### Changes in version 1.0.10:
* Fix typo causing an IllegalStateException #31 (by Divyanshu Sharma)
* Change to LazyRunConfigurationProducer, RunConfigurationProducer is deprecated
* pluginIcon (by Connectety)
* Minimum version 191.4212.41
* German translation
* MIT License #34

### Changes in version 1.0.7:
* Fixed IDEA 14 compatiblity (which broke when support for 15 was added)

### Changes in version 1.0.6: 
* Color scheme based on default IntelliJ (by Cezary Butler @ Programisci.eu)
* Dracula color scheme support (by Cezary Butler @ Programisci.eu)
* Fixed IDEA 14 compatiblity (which broke when support for 15 was added)

### Changes in version 1.0.5: 
* Support for Intellij IDEA 15 (by Alexander Kriegisch)

### Changes in version 1.0.4: 
* Support for Intellij IDEA 13
* Support for empty configurations
