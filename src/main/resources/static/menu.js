// JavaScript Document
var _LT_ajax_proxyXmlUrl = "http://srvfree.api.51ditu.com/apisrv/p4x?";

function LTNS() {
    LTNS.info = {time: 'Thu Jul 19 10:00:08 UTC+0800 2012', version: '1', ov: '1.3 Ver 20070705'};
    var w = function (uq) {
        var iq = 0, oq = 0;
        var pq = uq.length;
        var aq = new String();
        var sq = -1;
        var dq = 0;
        for (var fq = 0; fq < pq; fq++) {
            var gq = uq.charCodeAt(fq);
            gq = (gq == 95) ? 63 : ((gq == 44) ? 62 : ((gq >= 97) ? (gq - 61) : ((gq >= 65) ? (gq - 55) : (gq - 48))));
            oq = (oq << 6) + gq;
            iq += 6;
            while (iq >= 8) {
                var hq = oq >> (iq - 8);
                if (dq > 0) {
                    sq = (sq << 6) + (hq & (0x3f));
                    dq--;
                    if (dq == 0) {
                        aq += String.fromCharCode(sq);
                    }
                    ;
                } else {
                    if (hq >= 224) {
                        sq = hq & (0xf);
                        dq = 2;
                    } else if (hq >= 128) {
                        sq = hq & (0x1f);
                        dq = 1;
                    } else {
                        aq += String.fromCharCode(hq);
                    }
                    ;
                }
                ;oq = oq - (hq << (iq - 8));
                iq -= 8;
            }
            ;
        }
        ;
        return aq;
    };
    var q = ["post", "&charset=", "url=", "lt", "get", "undefined", "utf-8", "", "string", "error", "loaded", "complete", "interactive", "unload", "shape", 'function', "on", w("SsDoQN1q")];
    var i = window;
    var o = document;

    function yq(uq, iq) {
        for (var oq in iq) {
            uq[oq] = iq[oq];
        }
        ;
    }

    function a() {
    };

    function s(yq, uq) {
        return function () {
            return uq.apply(yq, arguments)
        };
    };

    function d(yq) {
        return (yq.tagName || yq == i || yq == o);
    };

    function f(yq) {
        return (yq && yq.ownerDocument && yq.ownerDocument.parentWindow) ? yq.ownerDocument.parentWindow : i;
    };

    function g(yq) {
        if (!yq) {
            yq = [];
        }
        ;
        if (!yq[0]) {
            yq[0] = f().event;
        }
        ;
        if (yq[0] && !yq[0].target && yq[0].srcElement) {
            yq[0].target = yq[0].srcElement
        }
        ;
        return yq;
    };

    function h(yq, uq) {
        return function () {
            uq.apply(yq, g(arguments));
        };
    };

    function j(yq) {
        yq = g(yq);
        if (!yq) {
            return;
        }
        ;
        if (yq.stopPropagation) {
            yq.preventDefault();
            yq.stopPropagation();
        } else if (o.all) {
            yq.cancelBubble = true;
            yq.returnValue = false;
        }
        ;
    };

    function k(yq) {
        yq = g(yq);
        if (!yq) {
            return;
        }
        ;
        if (o.all) {
            yq.cancelBubble = true;
            yq.returnValue = true;
        } else if (yq.stopPropagation) {
            yq.stopPropagation();
        }
        ;
    };

    function l(yq, event, uq, iq, oq) {
        return c(yq, event, d(yq) ? h(uq, iq) : s(uq, iq), oq);
    };

    function z(yq, uq) {
        if (!yq) {
            return;
        }
        ;
        if (yq.parentNode && !uq) {
            yq.parentNode.removeChild(yq);
        }
        ;b(yq);
        var iq;
        while (iq = yq.firstChild) {
            z(iq);
        }
        ;
    };

    function x(yq, uq) {
        return function () {
            var e = this;
            yq.apply(e, arguments);
            v(uq);
        }
    };

    function c(yq, event, uq, iq) {
        var oq = [yq, event];
        if (iq) {
            uq = x(uq, oq)
        }
        ;var pq = d(yq);
        if (pq) {
            uq = s(yq, uq);
            if (yq.addEventListener) {
                yq.addEventListener(event, uq, false);
            } else if (yq.attachEvent) {
                yq.attachEvent(q[16] + event, uq);
            } else {
                var aq = yq[q[16] + event];
                if (typeof (aq) == q[15]) {
                    yq[q[16] + event] = function () {
                        aq();
                        uq();
                    };
                } else {
                    yq[q[16] + event] = uq;
                }
                ;
            }
            ;
        }
        ;oq.push(uq);
        if (yq._LT_E_ && pq != q[14]) {
            yq._LT_E_.push(oq);
        } else {
            yq._LT_E_ = (pq == q[14]) ? [] : [oq];
        }
        ;
        if (!a.allEvents) {
            a.allEvents = [];
        }
        ;
        if (event != q[13]) {
            a.allEvents.push(oq);
        }
        ;
        return oq;
    };

    function v(yq) {
        if (!yq || yq.length == 0) {
            return;
        }
        ;
        if (arguments.length > 1) {
            var uq = arguments[0]._LT_E_;
            for (var iq = 0; iq < uq.length; iq++) {
                if (uq[iq][1] == arguments[1] && uq[iq][2] == arguments[2]) {
                    return v(uq[iq]);
                }
            }
            ;
        }
        ;
        try {
            if (d(yq[0])) {
                if (yq[0].removeEventListener) {
                    yq[0].removeEventListener(yq[1], yq[2], false);
                } else if (yq[0].detachEvent) {
                    yq[0].detachEvent(q[16] + yq[1], yq[2]);
                } else {
                    yq[0][q[16] + yq[1]] = null;
                }
                ;
            }
            ;var oq = yq[0]._LT_E_;
            for (var iq = oq.length - 1; iq >= 0; iq--) {
                if (oq[iq] == yq) {
                    oq.splice(iq, 1);
                    break;
                }
                ;
            }
            ;
        } catch (pq) {
        }
        ;oq = a.allEvents;
        for (var iq = oq.length - 1; iq >= 0; iq--) {
            if (oq[iq] == yq) {
                oq.splice(iq, 1);
                break;
            }
            ;
        }
        ;
        while (yq.length > 0) {
            yq.pop()
        }
        ;delete yq;
    };

    function b(yq, event) {
        if (!yq || !yq._LT_E_) {
            return;
        }
        ;var uq, iq = yq._LT_E_;
        for (var oq = iq.length - 1; oq >= 0; oq--) {
            uq = iq[oq];
            if (!event || uq[1] == event) {
                v(uq);
            }
            ;
        }
        ;
    };

    function n() {
        var yq = a.allEvents;
        if (yq) {
            for (var uq = yq.length - 1; uq >= 0; uq--) {
                v(yq[uq]);
            }
            ;
        }
        ;a.allEvents = null;
    };

    function m(yq, event, uq) {
        if (d(yq)) {
            try {
                if (yq.fireEvent) {
                    yq.fireEvent(q[16] + event);
                }
                ;
                if (yq.dispatchEvent) {
                    var iq = "mouseover,mouseout,mousemove,mousedown,mouseup,click,dbclick";
                    var oq = o.createEvent("Event");
                    oq.initEvent(event, false, true);
                    yq.dispatchEvent(oq);
                }
                ;
            } catch (pq) {
                alert('LTEvent.trigger error.');
            }
            ;
        } else {
            if (!uq) {
                uq = [];
            }
            ;var aq = yq._LT_E_;
            if (aq && aq.length > 0) {
                for (var sq = aq.length - 1; sq >= 0; sq--) {
                    var dq = aq[sq];
                    if (dq && dq[2]) {
                        if (dq[1] == "*") {
                            dq[2].apply(yq, [event, uq]);
                        }
                        ;
                        if (dq[1] == event) {
                            dq[2].apply(yq, uq);
                        }
                        ;
                    }
                    ;
                }
                ;
            }
            ;
        }
        ;
    };

    function _() {
        return o.all ? (o.readyState != "loading" && o.readyState != q[12]) : (a.readyState == q[11])
    };

    function Q() {
        if (!a.unLoadListener) {
            a.unLoadListener = c(i, q[13], n);
        }
        ;
        if (!o.all && !a.readyState) {
            a.readyState = q[12];
            c(o, "DOMContentLoaded", function () {
                a.readyState = q[11];
            }, true);
        }
        ;
    };yq(a, {
        getCallback: s,
        isHtmlControl: d,
        getObjWin: f,
        getWindowEvent: g,
        createAdapter: h,
        cancelBubble: j,
        returnTrue: k,
        bind: l,
        deposeNode: z,
        runOnceHandle: x,
        addListener: c,
        removeListener: v,
        clearListeners: b,
        clearAllListeners: n,
        trigger: m,
        isDocumentLoaded: _,
        load: Q
    });

    function W(yq, uq) {
        var e = this;
        e[0] = yq ? parseInt(yq) : 0;
        e[1] = uq ? parseInt(uq) : 0;
        e.Longitude = e[0];
        e.Latitude = e[1];
    };yq(W.prototype, {
        getLongitude: function () {
            var e = this;
            return e[0];
        }, getLatitude: function () {
            var e = this;
            return e[1];
        }, setLongitude: function (yq) {
            var e = this;
            e[0] = parseInt(yq);
        }, setLatitude: function (yq) {
            var e = this;
            e[1] = parseInt(yq);
        }
    });

    function E(yq) {
        var e = this;
        e.win = yq ? yq : i;
    };yq(E.prototype, {
        load: function (yq, uq, iq, oq) {
            var e = this;
            if (!e.onLoadStart(yq)) {
                return;
            }
            ;e.objName = oq ? oq : "_OLR";
            var pq = e.win;
            pq[e.objName] = null;
            var uq = uq ? uq : q[6];
            if (!e.jsFile) {
                e.jsFile = pq.document.createElement(q[17]);
                e.jsFile.type = w("T6LuT2zgONPXSsDoQN1q");
                e.jsFile.defer = true;
                pq.document.body.insertBefore(e.jsFile, pq.document.body.firstChild);
                l(e.jsFile, "readystatechange", e, e.onReadyStateChange);
                l(e.jsFile, "load", e, e.onLoad);
            }
            ;e.jsFile.charset = uq;
            e.jsFile.src = yq;
            e.running = true;
            e.crypt = iq;
        }, onLoadStart: function (yq) {
            var e = this;
            m(e, "loadstart", [yq]);
            return true;
        }, onLoad: function (yq) {
            var e = this;
            var uq = e.win;
            if (uq[e.objName]) {
                var iq = uq[e.objName];
                uq[e.objName] = null;
                m(e, q[10], [e.parseObject(iq)]);
            } else {
                m(e, q[9], []);
            }
            ;
            if (!o.all && e.jsFile && e.jsFile.parentNode == uq.document.body) {
                e.jsFile.removeAttribute("src");
                uq.document.body.removeChild(e.jsFile);
                delete e.jsFile;
            }
            ;e.running = false;
        }, parseObject: function (yq) {
            var e = this;
            if (e.crypt || yq.e) {
                U(yq);
            }
            ;
            return yq;
        }, onReadyStateChange: function (yq) {
            var e = this;
            if (!e.jsFile || (e.jsFile.readyState != q[10])) {
                return;
            }
            ;e.onLoad();
        }
    });

    function R(yq, uq, iq, oq) {
        var oq = oq ? oq : i;
        var pq = {url: yq, handle: uq, charset: iq, win: oq, classNum: 1};
        var aq = I(oq);
        l(aq, q[10], pq, T);
        l(aq, q[9], pq, T);
        aq.load(yq, iq);
    };

    function T(yq) {
        var e = this;
        e.classNum--;
        if (yq && yq._classUrls) {
            var uq;
            while (uq = yq._classUrls.pop()) {
                e.classNum++;
                R(uq, Y(e), e.charset, e.win);
            }
            ;
        }
        ;
        if (e.classNum == 0) {
            e.handle.apply(e);
        }
        ;
    };

    function Y(yq) {
        return function () {
            yq.classNum--;
            if (yq.classNum == 0) {
                yq.handle.apply(yq);
            }
            ;
        };
    };

    function U(yq) {
        var uq;
        if (yq.t) {
            yq.t = A(yq.t);
        }
        ;
        for (uq in yq.a) {
            if (typeof yq.a[uq] == q[8]) {
                yq.a[uq] = A(yq.a[uq]);
            }
            ;
        }
        ;
        for (uq in yq.c) {
            if (typeof yq.c[uq] != q[15]) {
                U(yq.c[uq]);
            }
            ;
        }
        ;
    };

    function I(yq) {
        var yq = yq ? yq : i;
        var uq;
        if (!yq._LT_OLRS) {
            yq._LT_OLRS = [];
        }
        ;
        for (var iq = 0; iq < yq._LT_OLRS.length; iq++) {
            if (yq._LT_OLRS[iq].running == false) {
                uq = yq._LT_OLRS[iq];
                b(uq);
                break;
            }
            ;
        }
        ;
        if (!uq) {
            uq = new E(yq);
            yq._LT_OLRS.push(uq);
            return uq;
        }
        ;uq.running = true;
        return uq;
    };

    function O(yq, uq) {
        for (var iq = 0; iq < yq.c.length; iq++) {
            if (yq.c[iq].n == uq) {
                return yq.c[iq];
            }
            ;
        }
        ;
    };

    function P(yq) {
        var uq = 0, iq = 0;
        var oq = yq.length;
        var pq = [];
        for (var aq = 0; aq < oq; aq++) {
            var sq = yq.charCodeAt(aq);
            if (sq >= 2048) {
                iq = (iq << 24) + (((sq >> 12) | 0xe0) << 16) + ((((sq & 0xfff) >> 6) | 0x80) << 8) + ((sq & 0x3f) | 0x80);
                uq += 24;
            } else if (sq >= 128) {
                iq = (iq << 16) + (((sq >> 6) | 0xc0) << 8) + ((sq & 0x3f) | 0x80);
                uq += 16;
            } else {
                uq += 8;
                iq = (iq << 8) + sq;
            }
            ;
            while (uq >= 6) {
                var dq = iq >> (uq - 6);
                iq = iq - (dq << (uq - 6));
                uq -= 6;
                var sq = (dq <= 9) ? (dq + 48) : ((dq <= 35) ? (dq + 55) : ((dq <= 61) ? (dq + 61) : ((dq == 62) ? 44 : 95)));
                pq.push(String.fromCharCode(sq));
            }
            ;
        }
        ;
        if (uq > 0) {
            var dq = iq << (6 - uq);
            pq.push(String.fromCharCode((dq <= 9) ? (dq + 48) : ((dq <= 35) ? (dq + 55) : ((dq <= 61) ? (dq + 61) : ((dq == 62) ? 44 : 95)))));
        }
        ;
        return pq.join(q[7]);
    };

    function A(yq) {
        var uq = 0, iq = 0;
        var oq = yq.length;
        var pq = [];
        var aq = -1;
        var sq = 0;
        for (var dq = 0; dq < oq; dq++) {
            var fq = yq.charCodeAt(dq);
            fq = (fq == 95) ? 63 : ((fq == 44) ? 62 : ((fq >= 97) ? (fq - 61) : ((fq >= 65) ? (fq - 55) : (fq - 48))));
            iq = (iq << 6) + fq;
            uq += 6;
            while (uq >= 8) {
                var gq = iq >> (uq - 8);
                if (sq > 0) {
                    aq = (aq << 6) + (gq & (0x3f));
                    sq--;
                    if (sq == 0) {
                        pq.push(String.fromCharCode(aq));
                    }
                    ;
                } else {
                    if (gq >= 224) {
                        aq = gq & (0xf);
                        sq = 2;
                    } else if (gq >= 128) {
                        aq = gq & (0x1f);
                        sq = 1;
                    } else {
                        pq.push(String.fromCharCode(gq));
                    }
                    ;
                }
                ;iq = iq - (gq << (uq - 8));
                uq -= 8;
            }
            ;
        }
        ;
        return pq.join(q[7]);
    };yq(E, {
        loadClass: R,
        onClassLoaded: T,
        onSubClassLoaded: Y,
        doDecrypt: U,
        getObject: I,
        getChild: O,
        encrypt: P,
        decrypt: A
    });

    function S() {
    };

    function D() {
        if (i.XMLHttpRequest) {
            return new XMLHttpRequest();
        } else if (typeof (ActiveXObject) != q[5]) {
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
        ;
    };

    function F(yq, uq) {
        var iq = D();
        iq.open(q[4], yq, true);
        iq.onreadystatechange = function () {
            if (iq.readyState != 4) {
                return;
            }
            ;var oq = iq.responseXML.xml ? iq.responseXML : L(iq.responseText);
            uq(oq);
        };
        iq.send(null);
    };

    function G(yq, uq, iq) {
        iq = iq ? iq : q[6];
        var oq = I();
        c(oq, q[10], function (pq) {
            if (pq.n == q[9] && pq.a.src == q[3]) {
                return;
            }
            ;var aq = X(pq);
            uq.apply(null, [aq]);
        });
        oq.load(i._LT_ajax_proxyXmlUrl + q[2] + encodeURIComponent(encodeURIComponent(yq)) + q[1] + iq);
    };

    function H(yq, uq) {
        var iq = D();
        iq.open(q[4], yq, true);
        iq.onreadystatechange = function () {
            if (iq.readyState != 4) {
                return;
            }
            ;uq(iq.responseText);
        };
        iq.send(null);
    };

    function J(yq, uq, iq) {
        var oq = iq ? iq.toLowerCase() == q[0] ? q[0] : pq : q[4];
        var aq = D();
        aq.open(oq, yq, true);
        aq.onreadystatechange = function () {
            if (aq.readyState != 4) {
                return;
            }
            ;uq(eval('(' + aq.responseText + ')'));
        };
        aq.send(null);
    };

    function K(yq, uq, iq) {
        iq = iq ? iq : q[6];
        var oq = I();
        c(oq, q[10], function (pq) {
            if (pq.n == q[9] && pq.a.src == q[3]) {
                return;
            }
            ;uq.apply(null, [A(pq)]);
        });
        oq.load(i._LT_ajax_proxyTextUrl + q[2] + encodeURIComponent(encodeURIComponent(yq)) + q[1] + iq);
    };

    function L(yq) {
        var uq;
        if (typeof (ActiveXObject) != q[5] && typeof (GetObject) != q[5]) {
            try {
                uq = new ActiveXObject("Msxml2.DOMDocument");
            } catch (iq) {
                uq = new ActiveXObject("Msxml.DOMDocument");
            }
            ;
            if (yq) {
                uq.loadXML(yq);
            }
            ;
        } else {
            if (yq) {
                if (typeof DOMParser != q[5]) {
                    uq = new DOMParser().parseFromString(yq, "text/xml")
                }
                ;
            } else {
                if (o.implementation && o.implementation.createDocument) {
                    uq = o.implementation.createDocument(q[7], q[7], null);
                }
                ;
            }
            ;
        }
        ;
        return uq;
    };

    function Z(yq, uq) {
        if (!uq) {
            yq.i = {};
            uq = yq;
        }
        ;
        if (yq.a.id) {
            uq.i[yq.a.id] = yq;
        }
        ;
        for (var iq = 0; iq < yq.c.length; iq++) {
            Z(yq.c[iq], uq);
        }
        ;
    };

    function X(yq) {
        var uq = L();
        if (yq) {
            uq.appendChild(C(yq, uq));
        }
        ;
        return uq;
    };

    function C(yq, uq) {
        var iq = uq.createElement(yq.n);
        for (var oq in yq.a) {
            iq.setAttribute(oq, yq.a[oq]);
        }
        ;
        for (var pq = 0; pq < yq.c.length; pq++) {
            iq.appendChild(C(yq.c[pq], uq));
        }
        ;
        if (yq.t) {
            iq.appendChild(uq.createTextNode(yq.t));
        }
        ;
        return iq;
    };

    function V(yq, uq) {
        if (typeof (yq) == q[8]) {
            yq = L(yq);
        }
        ;
        if (yq.documentElement) {
            yq = yq.documentElement;
        }
        ;var iq = {n: yq.nodeName, a: {}, c: []};
        if (!uq) {
            iq.i = {};
            uq = iq;
        }
        ;
        if (yq.attributes) {
            for (var oq = 0; oq < yq.attributes.length; oq++) {
                var pq = yq.attributes[oq].nodeName, aq = yq.attributes[oq].nodeValue;
                iq.a[pq] = aq;
                if (pq == "id") {
                    uq.i[aq] = iq;
                }
                ;
            }
            ;
        }
        ;
        for (var oq = 0; oq < yq.childNodes.length; oq++) {
            var sq = yq.childNodes[oq].nodeType;
            if (sq >= 3 && sq <= 6) {
                var dq = yq.childNodes[oq].nodeValue;
                if (!iq.t && !new RegExp("^[\\s]+$").test(dq)) {
                    iq.t = dq;
                }
                ;
            }
            ;
            if (sq == 1) {
                uq = uq ? uq : iq;
                iq.c.push(V(yq.childNodes[oq], uq));
            }
            ;
        }
        ;
        return iq;
    };

    function B(yq, uq) {
        var iq, oq = false;
        if (typeof yq.xml != q[5]) {
            try {
                iq = yq.selectNodes(uq);
            } catch (pq) {
                oq = true;
            }
            ;
        } else {
            oq = true;
        }
        ;
        if (!oq) {
            return iq;
        }
        ;var aq = yq.ownerDocument ? yq.ownerDocument : yq;
        var sq = aq.createNSResolver(aq.documentElement);
        var dq = aq.evaluate(uq, yq, sq, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
        iq = [];
        for (var fq = 0; fq < dq.snapshotLength; fq++) {
            iq.push(dq.snapshotItem(fq));
        }
        ;
        return iq;
    };

    function N(yq, uq) {
        var iq, oq = false;
        try {
            iq = yq.selectSingleNode(uq);
        } catch (pq) {
            oq = true;
        }
        ;
        if (!oq) {
            return iq;
        }
        ;
        return B(yq, uq)[0];
    };

    function M(yq) {
        return i.ActiveXObject ? yq.text : (yq.childNodes[0] ? yq.childNodes[0].nodeValue : q[7]);
    };

    function qq(yq, uq) {
        if (!yq.c || !yq.c.length) {
            return;
        }
        ;
        for (var iq = 0; iq < yq.c.length; iq++) {
            if (yq.c[iq].n == uq) {
                return yq.c[iq];
            }
            ;
        }
        ;
    };yq(S, {
        createHttpRequest: D,
        loadXml: F,
        loadRemoteXml: G,
        loadText: H,
        loadJson: J,
        loadRemoteText: K,
        createDocument: L,
        createJsonId: Z,
        toXml: X,
        toXmlNode: C,
        toJson: V,
        selectNodes: B,
        selectSingleNode: N,
        getNodeValue: M,
        getJsonChild: qq
    });

    function wq(yq, uq) {
        var e = this;
        e[0] = yq ? parseInt(yq) : 0;
        e[1] = uq ? parseInt(uq) : 0;
        e.EARTH_RADIUS = 6378137;
        e.PI = Math.PI;
        e.js_point = {x: e[0], y: e[1]};
        e.point = e.WGS84ToMercator(e.js_point);
        e[0] = e.point.x;
        e[1] = e.point.y;
        e.Longitude = e[0];
        e.Latitude = e[1];
    };yq(wq.prototype, {
        getLongitude: function () {
            var e = this;
            return e[0];
        }, getLatitude: function () {
            var e = this;
            return e[1];
        }, setLongitude: function (yq) {
            var e = this;
            e[0] = parseInt(yq);
        }, setLatitude: function (yq) {
            var e = this;
            e[1] = parseInt(yq);
        }, WGS84ToMercator: function (yq) {
            var e = this;
            var uq = yq;
            var iq = yq.x / 100000.0;
            var oq = yq.y / 100000.0;
            var pq = e.PI * e.EARTH_RADIUS;
            var aq = iq * pq / 180.0;
            var sq = Math.log(Math.tan((90 + oq) * e.PI / 360.0)) / (e.PI / 180.0);
            sq = sq * pq / 180.0;
            uq.x = Math.round(aq);
            uq.y = Math.round(sq);
            return uq;
        }
    });

    function eq(yq, uq) {
        var e = this;
        e[0] = yq ? parseInt(yq) : 0;
        e[1] = uq ? parseInt(uq) : 0;
        e.EARTH_RADIUS = 6378137;
        e.PI = Math.PI;
        e.js_point = {x: e[0], y: e[1]};
        e.point = e.MercatorToWGS84(e.js_point);
        e[0] = e.point.x;
        e[1] = e.point.y;
        e.Longitude = e[0];
        e.Latitude = e[1];
    };yq(eq.prototype, {
        getLongitude: function () {
            var e = this;
            return e[0];
        }, getLatitude: function () {
            var e = this;
            return e[1];
        }, setLongitude: function (yq) {
            var e = this;
            e[0] = parseInt(yq);
        }, setLatitude: function (yq) {
            var e = this;
            e[1] = parseInt(yq);
        }, MercatorToWGS84: function (yq) {
            var e = this;
            var uq = yq;
            var iq = e.PI * e.EARTH_RADIUS / 180.0;
            var oq = yq.x / iq;
            var pq = yq.y / iq;
            pq = 180.0 / e.PI * (2 * Math.atan(Math.exp(pq * e.PI / 180.0)) - e.PI / 2.0);
            uq.x = Math.round(oq * 100000.0);
            uq.y = Math.round(pq * 100000.0);
            return uq;
        }
    });

    function rq() {
        var e = this;
        e.length = 0;
        e.prefix = "shi_2011";
    };yq(rq.prototype, {
        put: function (yq, uq) {
            var e = this;
            e[e.prefix + yq] = uq;
            e.length++;
        }, get: function (yq) {
            var e = this;
            return typeof e[e.prefix + yq] == q[5] ? null : e[e.prefix + yq];
        }, keySet: function () {
            var e = this;
            var yq = new Array();
            var uq = 0;
            for (var iq in e) {
                if (iq.substring(0, e.prefix.length) == e.prefix) {
                    yq[uq++] = iq.substring(e.prefix.length);
                }
                ;
            }
            ;
            return yq.length == 0 ? null : yq;
        }, values: function () {
            var e = this;
            var yq = new Array();
            var uq = 0;
            for (var iq in e) {
                if (iq.substring(0, e.prefix.length) == e.prefix) {
                    yq[uq++] = e[iq];
                }
                ;
            }
            ;
            return yq.length == 0 ? null : yq;
        }, size: function () {
            var e = this;
            return e.length;
        }, remove: function (yq) {
            var e = this;
            delete e[e.prefix + yq];
            e.length--;
        }, update: function (yq, uq) {
            var e = this;
            e[e.prefix + yq] = uq;
        }, clear: function () {
            var e = this;
            for (var yq in e) {
                if (yq.substring(0, e.prefix.length) == e.prefix) {
                    delete e[yq];
                }
                ;
            }
            ;e.length = 0;
        }, isEmpty: function () {
            var e = this;
            return e.length == 0;
        }, containsKey: function (yq) {
            var e = this;
            for (var uq in e) {
                if (uq == e.prefix + yq) {
                    return true;
                }
                ;
            }
            ;
            return false;
        }, containsValue: function (yq) {
            var e = this;
            for (var uq in e) {
                if (e[uq] == yq) {
                    return true;
                }
                ;
            }
            ;
            return false;
        }, putAll: function (yq) {
            var e = this;
            if (yq == null) return;
            if (yq.constructor != rq) {
                return;
            }
            ;var uq = yq.keySet();
            var iq = yq.values();
            for (var oq in uq) {
                e.put(uq[oq], iq[oq]);
            }
            ;
        }
    });

    function tq() {
        var e = this;
        e.base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        e.base64DecodeChars = new Array(-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
    };yq(tq.prototype, {
        base64encode: function (yq) {
            var uq, iq, oq;
            var pq, aq, sq;
            oq = yq.length;
            iq = 0;
            uq = q[7];
            while (iq < oq) {
                pq = yq.charCodeAt(iq++) & 0xff;
                if (iq == oq) {
                    uq += tq.base64EncodeChars.charAt(pq >> 2);
                    uq += tq.base64EncodeChars.charAt((pq & 0x3) << 4);
                    uq += "==";
                    break;
                }
                ;aq = yq.charCodeAt(iq++);
                if (iq == oq) {
                    uq += tq.base64EncodeChars.charAt(pq >> 2);
                    uq += tq.base64EncodeChars.charAt(((pq & 0x3) << 4) | ((aq & 0xF0) >> 4));
                    uq += tq.base64EncodeChars.charAt((aq & 0xF) << 2);
                    uq += "=";
                    break;
                }
                ;sq = yq.charCodeAt(iq++);
                uq += tq.base64EncodeChars.charAt(pq >> 2);
                uq += tq.base64EncodeChars.charAt(((pq & 0x3) << 4) | ((aq & 0xF0) >> 4));
                uq += tq.base64EncodeChars.charAt(((aq & 0xF) << 2) | ((sq & 0xC0) >> 6));
                uq += tq.base64EncodeChars.charAt(sq & 0x3F);
            }
            ;
            return uq;
        }, base64decode: function (yq) {
            var e = this;
            var uq, iq, oq, pq;
            var aq, sq, dq;
            sq = yq.length;
            aq = 0;
            dq = q[7];
            while (aq < sq) {
                do {
                    uq = e.base64DecodeChars[yq.charCodeAt(aq++) & 0xff];
                } while (aq < sq && uq == -1);
                if (uq == -1) break;
                do {
                    iq = e.base64DecodeChars[yq.charCodeAt(aq++) & 0xff];
                } while (aq < sq && iq == -1);
                if (iq == -1) break;
                dq += String.fromCharCode((uq << 2) | ((iq & 0x30) >> 4));
                do {
                    oq = yq.charCodeAt(aq++) & 0xff;
                    if (oq == 61) return dq;
                    oq = e.base64DecodeChars[oq];
                } while (aq < sq && oq == -1);
                if (oq == -1) break;
                dq += String.fromCharCode(((iq & 0XF) << 4) | ((oq & 0x3C) >> 2));
                do {
                    pq = yq.charCodeAt(aq++) & 0xff;
                    if (pq == 61) return dq;
                    pq = e.base64DecodeChars[pq];
                } while (aq < sq && pq == -1);
                if (pq == -1) break;
                dq += String.fromCharCode(((oq & 0x03) << 6) | pq);
            }
            ;
            return dq;
        }, utf16to8: function (yq) {
            var uq, iq, oq, pq;
            uq = q[7];
            oq = yq.length;
            for (iq = 0; iq < oq; iq++) {
                pq = yq.charCodeAt(iq);
                if ((pq >= 0x0001) && (pq <= 0x007F)) {
                    uq += yq.charAt(iq);
                } else if (pq > 0x07FF) {
                    uq += String.fromCharCode(0xE0 | ((pq >> 12) & 0x0F));
                    uq += String.fromCharCode(0x80 | ((pq >> 6) & 0x3F));
                    uq += String.fromCharCode(0x80 | ((pq >> 0) & 0x3F));
                } else {
                    uq += String.fromCharCode(0xC0 | ((pq >> 6) & 0x1F));
                    uq += String.fromCharCode(0x80 | ((pq >> 0) & 0x3F));
                }
                ;
            }
            ;
            return uq;
        }, utf8to16: function (yq) {
            var uq, iq, oq, pq;
            var aq, sq;
            uq = q[7];
            oq = yq.length;
            iq = 0;
            while (iq < oq) {
                pq = yq.charCodeAt(iq++);
                switch (pq >> 4) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                        uq += yq.charAt(iq - 1);
                        break;
                    case 12:
                    case 13:
                        aq = yq.charCodeAt(iq++);
                        uq += String.fromCharCode(((pq & 0x1F) << 6) | (aq & 0x3F));
                        break;
                    case 14:
                        aq = yq.charCodeAt(iq++);
                        sq = yq.charCodeAt(iq++);
                        uq += String.fromCharCode(((pq & 0x0F) << 12) | ((aq & 0x3F) << 6) | ((sq & 0x3F) << 0));
                        break;
                }
                ;
            }
            ;
            return uq;
        }
    });
    var p = function (a) {
        var s = o.getElementsByTagName(q[17]);
        var d = new RegExp(a, "i");
        for (var f = 0; f < s.length; f++) {
            var g = s[f];
            if (g.src && d.test(g.src)) {
                break;
            }
            ;
        }
        ;
        return !o.all || f < s.length;
    };
    if (!p(w("NbnpAYXeT7HmA3ywSoa_EYylAJyeFpfRN7TTArmkAIerCMHfT7LSBcDlRIzgSozXQc5uN2vgSrnpAYG"))) return false;
    yq(i, {
        LTEvent: a,
        LTPoint: W,
        LTObjectLoader: E,
        LTAjax: S,
        LTPointMercator: wq,
        LTPointWGS84: eq,
        LTHashMap: rq,
        LTBase64: tq
    });
    Q();
};LTNS();