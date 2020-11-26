# Databinding Validator

[![](https://jitpack.io/v/schatzlabs/databinding-validator.svg)](https://jitpack.io/#schatzlabs/databinding-validator)
![Android CI](https://github.com/schatzlabs/databinding-validator/workflows/Android%20CI/badge.svg)

This is an Android databinding library for validating Input fields written in Kotlin.

## How it works:

### Step 1. Add the JitPack repository to your project's build file

```sh
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

### Step 2. Add the library to you dependencies section in your app's build.gradle file

```sh
dependencies {
    implementation 'com.github.schatzlabs:databinding-validator:1.0.0-alpha01'
}
```

### Make sure you have enabled DataBinding for your project in your app's build.gradle file

```sh
dataBinding { 
   enabled true 
}
```

Then sync your project to download the dependencies.

### Step 3. Include the binding adapters in your layout file

```sh
<EditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    android:layout_marginStart="16dp"
    android:layout_marginTop="16dp"
    android:layout_marginEnd="16dp"
    android:autofillHints="example@email.com"
    android:hint="@string/email_address"
    android:inputType="textEmailAddress"
    app:validateEmpty="@{true}"
    app:validateType="@{`Email`}"
    app:validateTypeAutoDismiss="@{true}" />
```

```sh
app:validateEmpty 
app:validateType 
app:validateTypeAutoDismiss
```

These attributes here utilize our Validator library which binds and adds validation to the input fields .

### Step 4. Initialize our Validator

```sh
    // Declare our Validator class variable
    private lateinit var validator: Validator
    
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_login)
        
        // Initialize our Validator class and pass the view binding to our constructor
        // This binds our view with the binding adapters
        validator = Validator(binding)

        // Enable form validation mode: validates all inputs at once, like submit forms on web browsers
        validator.enableFormValidationMode()
        
        binding.buttonLogin.setOnClickListener {
            // Validate that our inputs are valid data
            if (validator.validate()) {
                // Submit your form with your validated input fields
            }
        }
    }
```


#### We are all set!!

Now we can enjoy databinding and validation on our input values right out of the box.

#### Other Attributes we can use

```sh
validateDate
validateMinLength
validateMaxLength
validateEmpty
validateType
```

More coming soon

### Support

<a href="https://www.buymeacoffee.com/zakayothuku" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-black.png" alt="Buy Me A Coffee" style="height: 41px !important;width: 174px !important;" ></a>

### Contributing

All our development (both new features and bug fixes) is performed in the develop branch. This way master always contains the sources of the most recently released version. Please send PRs with bug fixes to the develop branch. Documentation fixes in the markdown files are an exception to this rule. They are updated directly in master.

The develop branch is pushed to master on release.

For more details on contributing please see our contributing guide.

### License

```sh
  Copyright 2020 Schatz Designs
 
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
```
