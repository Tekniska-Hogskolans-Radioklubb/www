# THR-www
Static website prototype for Tekniska Högskolans Radioklubb.

## Static export
Export a static version of the site to the `dist` directory:
```
$ clj -m thr.www.main export --output dist
```

## Serving the site locally
If you don't have a REPL running, or just want something up and running quickly,
you can start a development webserver from the command line. The server will attempt
to reload modified code on every request, so that you don't have to restart it so often.
Don't use this to serve the site in a production environment of any kind.
```
$ clj -m thr.www.main serve --port 3000
```


## REPL driven development
There are some useful functions in the `user` namespace for REPL driven development:

- `(start)` Starts the dev server
- `(stop)` Stops the dev server
- `(reset)` Reloads changed namespaces and restarts the dev server
