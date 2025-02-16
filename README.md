# Набор моделей для работы с географическими данными

## Установка Maven
```xml 
  <dependency>
    <groupId>ru.myosin</groupId>
    <artifactId>geo-model</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Установка Kotlin multiplatform
```kotlin
  val commonMain by getting {
        dependencies {
            implementation("ru.myosin:geo-model:1.0.0")
        }
    }
```

## Установка Kotlin multiplatform

## Установка Kotlin multiplatform
```toml
[versions]
myosin = "1.0.0"

[libraries]
geo-model = { id = "ru.myosin:geo-model", version.ref = "myosin" }
```


```kotlin
  val commonMain by getting {
        dependencies {
            implementation(libs.geo.model)
        }
    }
```



