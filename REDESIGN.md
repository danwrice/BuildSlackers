### Problem Statement

A basic fundamental need of software systems which evolve over time is the idea of "designing for failure". The gold standard for software availability is the "5 9's", meaning that the system will be up 99.999% of the time. Most big projects will have dependencies on third-party software. Those third-party software piees will be updated over time, providing gains in performance, security, or reliability, among other things. To ensure your software is as up to date as possible, you will occasionally need to update your software so that it is using the latest version of any software it is dependent on. 

Incompatibility issues can often arise from updating versions of different dependencies in a software project, which leads to needless downtime. When this occurs it can take a long time for developers to dig through every dependency that has been updated, identify the specific one that caused the build to break, and determine what needs to be done to fix it. BuildSlackers automates this process so that not only will developers not need to waste time determining what dependency upgrades caused a build to break, but the build will not even break from dependency incompatibility in the first place.

### Bot Description

BuildSlackers will simplify the process of updating the dependencies of a project through automation. When a user decides that they would like to update a project's dependencies, they will contact the bot through Slack, telling it "Update dependencies" as well as the name of the project (GitHub repo name) they want updated. BuildSlackers clones the repository, then searches the repository (Need to determine how it's going to identify Eclipse project dependencies) identifying all of the different dependencies. It then uses some kind of package manager (To be determined) to compile a list of all dependencies which have newer versions available than those currently in the project. Once it has determined the software packages that could be updated, BuildSlackers creates testing folders for each individual version update. It then performs each respective update, and builds the project within the testing folder, redirecting the build result into log files. All errors and warnings are extracted and placed into separate log files so they may be easily located. Additionally, BuildSlackers runs any unit tests within a project's test folder, saving those results into a separate log file. Once all of the builds and tests have completed, BuildSlackers combines the warnings/errors results with the testing results into a single result file which it then uploads to Slack (or GitHub). This process of testing each newer released version is repeated for every single dependent package referenced by the software project. Once all dependencies have been checked, users are notified that the dependency update checking is complete through Slack.
  
##### Design Documents

###### Wireframe


##### Storyboard


### Architecture Design


Constraints:
- The bot will only fix dependency incompatibility upgrade issues in Java code. If a project has non-Java files that need upgrading, BuildSlackers will not handle that.

##### Additional Patterns
Our bot was inspired by ApiMonkey, built by Tanvi Mainkar. ApiMonkey is a dependency tool for C# code which does essentially the same thing as BuildSlackers, only in a different language. It can be found at: https://github.com/alt-code/ApiMonkey