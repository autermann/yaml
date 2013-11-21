# YAML API [![Build Status](https://travis-ci.org/autermann/yaml.png?branch=master)](https://travis-ci.org/autermann/yaml)

A [Jackson][jackson]-like API to create YAML nodes (based on [SnakeYAML][snakeyaml]).

## Example Usage

### Maven

Add this to your `dependencies`:
```xml
<dependency>
     <groupId>com.github.autermann</groupId>
     <artifactId>yaml</artifactId>
     <version>1.0.0</version>
</dependency>
```

### Creating nodes
```java
YamlNodeFactory factory = YamlNodeFactory.createDefault();
YamlMapNode mnode = factory.orderedMapNode();
mnode.put("hello", "world")
     .put("key", true)
     .put("integer", 2)
     .put("double", 42.0d)
     .put("time", DateTime.now(DateTimeZone.UTC));
mnode.putSequence("message")
     .add("this").add("is").add("snakeyaml-api");
mnode.putPairs("pairs")
     .put("pair1", "pair1")
     .put("pair1", "pair1")
     .put("pair2", "pair2");

byte[] bytes = new byte[240];
new Random().nextBytes(bytes);
mnode.put("binary", bytes);

mnode.dump(System.out);
```

```yaml
!!omap
hello: world
key: true
integer: 2
double: 42.0
time: 2013-11-06T02:33:08.701Z
message: [this, is, snakeyaml-api]
pairs: !!pairs
  pair1: pair1
  pair1: pair1
  pair2: pair2
binary: !!binary |-
  gePJ2SRk0zJFl3B/pD+jDSqAVwZcusf36lZ/LrTHA6fkBna9OJAXhItSf+VP2ORk92LIF2ldTAFFZaUu
  KNto57j82DpT2aM4ty6Ta5WE1RcOFtT+Sfc3VC0t5JfvTFQVMuvsWvvxLQ7/1W/LHKswQbWb7dwsGgYs
  O1DqFGnkJgTHH+7pp1yNf4G1A/SchS9stc0us3OzjAku/b8xSdlgRLMz195F9mEDvfW/BGTvQLjbSSIZ
  Nr5w92nw1ecXCni2dUCNk7VQERQVA8fB1YN89XluLuW+xY4kcATEj6+CzuRRJK6NS90kJ1SmM6/mDOu3
```

### Reading Nodes
```java
YamlNode loaded = new Yaml().load(/*...*/);
if (loaded.isMapping()) {
    YamlMappingNode node = loaded.asMapping();
    node.path("hello").textValue(); // world
    node.path("binary").binaryValue(); // byte[240]
    node.path("message").asSequence().get(2); //snakeyaml-api
}
```

## License
The project is licensed under the [Apache License, Version 2.0][apache]
```
Copyright 2013 Christian Autermann

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

[apache]: http://www.apache.org/licenses/LICENSE-2.0 "Apache License, Version 2.0"
[jackson]: http://jackson.codehaus.org/ "Jackson"
[snakeyaml]: https://code.google.com/p/snakeyaml/ "SnakeYAML"
