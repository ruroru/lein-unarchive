# unarchive

A Leiningen plugin to extract tar.gz and zip archives.

## installation
Add unarchive plugin to plugin list:
```clojure
[org.clojars.jj/unarchive "1.0.0"]
```

## Usage
Add files to download to project.clj
```clojure
:unarchive [{:source "file1.zip"}
            {:source "target/file.tar.gz"}]
```

```bash
lein unarchive
```

## License

Copyright © 2025 [ruroru](https://github.com/ruroru)

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
