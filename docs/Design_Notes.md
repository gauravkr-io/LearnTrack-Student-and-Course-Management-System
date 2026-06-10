# Design Notes

## Why ArrayList over array?

ArrayList was chosen because it provides a dynamic collection with flexible sizing and convenient methods for searching, filtering, and modifying entities. It simplifies storage management compared to a plain array, which would require manual resizing and more boilerplate.

## Why static members were used for IdGenerator counters?

The ID counters are static so they remain shared across the application and generate unique values for every entity type. This keeps the ID generator simple and ensures the values persist without requiring an object instance.

## What is gained from Person → Student/Trainer inheritance?

The inheritance hierarchy reduces duplicate code by centralizing shared identity fields and behavior in `Person`. It also clarifies that students and trainers are both people with different domain-specific details.

## Why does the repository layer exist separately from the service layer?

The repository layer handles raw data storage and retrieval, while the service layer encapsulates business rules and validation. Separating them improves maintainability and keeps the codebase aligned with clean architecture principles.

## Why Optional was used instead of returning null?

Optional explicitly expresses when a lookup may fail, avoiding null pointer risks and making absence handling safer and more intentional. It also encourages the caller to deal with the missing value rather than assuming it is present.
