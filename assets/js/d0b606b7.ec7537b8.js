(window.webpackJsonp=window.webpackJsonp||[]).push([[14],{81:function(e,t,n){"use strict";n.r(t),n.d(t,"frontMatter",(function(){return o})),n.d(t,"metadata",(function(){return l})),n.d(t,"toc",(function(){return p})),n.d(t,"default",(function(){return s}));var a=n(3),r=n(7),i=(n(0),n(88)),o={title:"Path Variables",slug:"/framework/path-variables"},l={unversionedId:"path-variables",id:"path-variables",isDocsHomePage:!1,title:"Path Variables",description:"Like RequestBody, Kleuth uses Spring's PathVariable annotation to include path variables in the url path.",source:"@site/docs/path-variables.md",slug:"/framework/path-variables",permalink:"/kleuth/docs/framework/path-variables",editUrl:"https://github.com/alienhead/kleuth/edit/master/documentation/docs/path-variables.md",version:"current",sidebar:"docs",previous:{title:"Handling Requests",permalink:"/kleuth/docs/framework/request-handling"},next:{title:"Content Type",permalink:"/kleuth/docs/framework/content-type"}},p=[],c={toc:p};function s(e){var t=e.components,n=Object(r.a)(e,["components"]);return Object(i.b)("wrapper",Object(a.a)({},c,n,{components:t,mdxType:"MDXLayout"}),Object(i.b)("p",null,"Like ",Object(i.b)("inlineCode",{parentName:"p"},"RequestBody"),", Kleuth uses Spring's ",Object(i.b)("inlineCode",{parentName:"p"},"PathVariable")," annotation to include path variables in the url path."),Object(i.b)("p",null,"Mark function parameters as path variables the same as if creating the request mapping with the Spring ",Object(i.b)("inlineCode",{parentName:"p"},"RequestMapping")," annotation:"),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="/pizzas/GetPizzaById.kt"',title:'"/pizzas/GetPizzaById.kt"'},'@Route\nclass GetPizzaById(\n    private val service: PizzaService\n) {\n    fun get(@PathVariable id: UUID): ResponseEntity<Pizza> {\n        val pizza = service.findById(id) ?: throw Exception("Pizza not found")\n        return ResponseEntity.ok(pizza)\n    }\n}\n')),Object(i.b)("p",null,"The route will map to ",Object(i.b)("inlineCode",{parentName:"p"},"/pizzas/{id}"),"."),Object(i.b)("p",null,"Kleuth will also see if the value/name of the ",Object(i.b)("inlineCode",{parentName:"p"},"PathVariable")," annotation has been set:"),Object(i.b)("pre",null,Object(i.b)("code",{parentName:"pre",className:"language-kotlin",metastring:'title="/pizzas/GetPizzaById.kt"',title:'"/pizzas/GetPizzaById.kt"'},'@Route\nclass GetPizzaById(\n    private val service: PizzaService\n) {\n    fun get(@PathVariable(name = "pizzaId") id: UUID): ResponseEntity<Pizza> {\n        val pizza = service.findById(id) ?: throw Exception("Pizza not found")\n        return ResponseEntity.ok(pizza)\n    }\n}\n')),Object(i.b)("p",null,"The route will correctly map to ",Object(i.b)("inlineCode",{parentName:"p"},"/pizzas/{pizzaId}"),"."))}s.isMDXComponent=!0},88:function(e,t,n){"use strict";n.d(t,"a",(function(){return u})),n.d(t,"b",(function(){return f}));var a=n(0),r=n.n(a);function i(e,t,n){return t in e?Object.defineProperty(e,t,{value:n,enumerable:!0,configurable:!0,writable:!0}):e[t]=n,e}function o(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(e);t&&(a=a.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,a)}return n}function l(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?o(Object(n),!0).forEach((function(t){i(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):o(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}function p(e,t){if(null==e)return{};var n,a,r=function(e,t){if(null==e)return{};var n,a,r={},i=Object.keys(e);for(a=0;a<i.length;a++)n=i[a],t.indexOf(n)>=0||(r[n]=e[n]);return r}(e,t);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);for(a=0;a<i.length;a++)n=i[a],t.indexOf(n)>=0||Object.prototype.propertyIsEnumerable.call(e,n)&&(r[n]=e[n])}return r}var c=r.a.createContext({}),s=function(e){var t=r.a.useContext(c),n=t;return e&&(n="function"==typeof e?e(t):l(l({},t),e)),n},u=function(e){var t=s(e.components);return r.a.createElement(c.Provider,{value:t},e.children)},b={inlineCode:"code",wrapper:function(e){var t=e.children;return r.a.createElement(r.a.Fragment,{},t)}},d=r.a.forwardRef((function(e,t){var n=e.components,a=e.mdxType,i=e.originalType,o=e.parentName,c=p(e,["components","mdxType","originalType","parentName"]),u=s(n),d=a,f=u["".concat(o,".").concat(d)]||u[d]||b[d]||i;return n?r.a.createElement(f,l(l({ref:t},c),{},{components:n})):r.a.createElement(f,l({ref:t},c))}));function f(e,t){var n=arguments,a=t&&t.mdxType;if("string"==typeof e||a){var i=n.length,o=new Array(i);o[0]=d;var l={};for(var p in t)hasOwnProperty.call(t,p)&&(l[p]=t[p]);l.originalType=e,l.mdxType="string"==typeof e?e:a,o[1]=l;for(var c=2;c<i;c++)o[c]=n[c];return r.a.createElement.apply(null,o)}return r.a.createElement.apply(null,n)}d.displayName="MDXCreateElement"}}]);