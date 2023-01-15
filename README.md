## Problem

Often, there is a need to enforce password policies like, strength requirements, expiry days, remaining-days warning, and so on 

Way back in time, I was asked to build a Policy Service in the framework which was planned to be used in all the applications built upon it


## Requirements

Since the policy service was planned to be adjustable for multiple applications so, it has to be generic and configurable


## Solution

The solution I originally built was storing the configuration in the Database
For simplicity, I have built this example by storing configuration in properties i.e. `application-policy.properties`


````
# specifies minimum requirements for new password
app.password-policy.min-special-chars=2
app.password-policy.min-numbers=2
app.password-policy.min-capital-letters=2
app.password-policy.min-length=6

app.password-policy.allowed-special-chars=\!\@\#\$\%\^\&\*\(\)\<\,\.\>\?\[\]\{\}

# specifies password change duration
app.password-policy.password-expiry-days=30
app.password-policy.show-warning-in-days=5
````

Above configuration will be used for policy enforcement.
You can use database configurations if you like

## Working

...... `Work is under progress, the section will be updated soon`