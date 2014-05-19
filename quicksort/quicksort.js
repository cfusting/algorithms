function qs(a, l, r, selectPivot) {
    if (r - l <= 0) return(0);
    var cp = r - l - 1;
    var p = selectPivot(a, l, r);
    console.log('Pivot value: ' + a[p]);
    var m = prt(a, l, r, p);
    cp = cp + qs(a, l, m, selectPivot);
    cp = cp + qs(a, m + 1, r, selectPivot);
    return(cp);
}

function prt(a, l, r, p) {
    console.log('Parting: ' + a.slice(l, r));
    var t = a[l];
    a[l] = a[p];
    a[p] = t;
    var pivotValue = a[l];
    var i = l + 1;
    for (var q = i; q < r; q++) {
        if (a[q] < pivotValue) {
            console.log('Swapping ' + a[q] + ' and ' + a[i]);
            var t = a[i];
            a[i] = a[q];
            a[q] = t;
            i = i + 1;
        }
    }
    t = a[l];
    a[l] = a[i - 1];
    a[i - 1] = t;
    console.log('Parted: ' + a.slice(l, r));
    return(i - 1);
}

function selectFirstPivot(a, l, r) {
    return(l);
}

function selectLastPivot(a, l, r) {
    return(r - 1);
}

function selectMedianPivot(a, l, r) {
    var m = Math.floor((l + r) / 2);
    var v = [];
    v[0] = a[l];
    v[1] = a[m];
    v[2] = a[r];
    v.sort();
    var p = v[1];
    if (a[l] == p) {
        return(l);
    } else if (a[m] == p) {
        return(m);
    } else {
        return(r);
    }
}

var a = [3,9,8,4,6,10,2,5,7,1];
var csv = require('fast-csv');
csv.fromPath('qs10.txt').on('record', function(data) { a.push(parseInt(data)) });
console.log(a);
var cp = qs(a, 0, a.length, selectLastPivot);
console.log(a);
console.log(cp);
