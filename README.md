## What is this?

This is a repository that demonstrates how to use Clojure libraries: Toucan, HoneySQL
This repository uses Uses [Clerk](https://github.com/nextjournal/clerk) to show up as notebook.

## Where to see this stuff?

https://sindhubb.github.io/clerk-databases/

## Pre-requisites
1. Ensure you have Postgres installed
2. Create a postgres user called 'chef'
3. Create a database called recipes
    Make chef the owner of recipes
4. From this repo, run `lein migrate -- --migrate`. 
    This will create all the required tables and seed data. 

## How to run this to contribute?

1. Install dependencies with `lein deps` 
2. Ensure you have Calva installed on your VSCode
3. Start Calva Leiningen project REPL
4. Add this to this repository's `.vscode` into `settings.json`:

```
{
    "calva.jackInEnv": {
    },
    "calva.customREPLCommandSnippets": [
        {
            "name": "Open in Clerk",
            "key": "d",
            "snippet": "(require '[nextjournal.clerk :refer [show!]]) (show! \"$file\")"
        },
        {
            "name": "Start Clerk",
            "key": "s",
            "snippet": "(require '[nextjournal.clerk :refer [serve!]])  (serve! {:port <PORT> :watch-paths [\"src/databases\"]})"
        }
    ],
}
```
Remember to replace <PORT> with your desired port. 

5. Press these keys: 

```
control + option + space   - starts custom calva commands
  s -> start clerk
  d -> open current file in clerk
```
6. Browse `http://localhost:3755` to see the live notebook

## How to get HTML?

Run `lein make-docs` and you should see html files generated under `src/databases`

## How to view HTML?

You can open `./docs/index.html` in any browser and browse as you normally would. You can also serve these HTML files with `lein ring server-headless <PORT>` 

## TODO
* design a recipes database 
    ?
* add things to utilise in honeysql 
* add things to utilise in toucan  
* how to honeysql?
* how to toucan?
* how to connection pool?
* how to pg rls?
* how to clickhouse?
