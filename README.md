## What is this?

Clerk + databases includes:
* toucan
* honeysql 

## How to run this for contrib?

Uses [Clerk](https://github.com/nextjournal/clerk) to show up as notebook.

Run `clj` in this directory, this will install all dependencies (from `deps.edn`)

Calva is required to start Clerk. Use the follow commands in vscode:

```
control + option + space   - starts custom calva commands
  s -> start clerk
  d -> open current file in clerk
```

## How to get HTML?

Run `lein make-docs` and you should see html files generated under `src/databases`

## How to view HTML?

You can open `./docs/index.html` in any browser and browse as you normally would. You can also serve these HTML files with `lein ring server-headless <PORT>` 