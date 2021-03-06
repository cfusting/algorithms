function qs!(a, l, n, getpivot)
    if n - l <= 0 return(0) end
    #println("Quicksorting from $l to $n")
    cp = n - l
    #println("Comparisons: $cp")
    p = getpivot(a, l, n) 
    #println("Pivot index: $p")
    a[l], a[p] = a[p], a[l]
    m = prt!(a, l, n)
    cp = cp + qs!(a, l, m - 1, getpivot)
    cp = cp + qs!(a, m + 1, n, getpivot)
    return(cp)
end

function getfirstpivot(a, l, r)
    return(l)
end

function getlastpivot(a, l, r)
    return(r)
end

function getmedianpivot(a, l, r)
    #println("Looking for median pivot: $(a[l:r]')")
    m = ifloor((l + r) / 2)
    #println("Selecting median pivot from $(a[l]) $(a[m]) $(a[r])") 
    p = median(a[[l, m, r]]) 
    #println("Found pivot value $p")
    if p == a[l]
        return(l)
    elseif p == a[m]
        return(m)
    elseif p == a[r]
        return(r)
    end
end

function prt!(a, l, r)
    #println("Parting: $(a[l:r]')")
    p = a[l]
    i = l + 1
    for j = l + 1:r
        if a[j] < p
            a[i], a[j] = a[j], a[i]
            i = i + 1
        end
    end
    a[i - 1], a[l] = a[l], a[i - 1]
    #println("Parted: $(a[l:r]')")
    return(i - 1)
end

a = readcsv("QuickSort.txt", Int64)
b = copy(a)
c = copy(a)
d = copy(a)
sanity = sortrows(a)

using Base.Test
# Test median functions
@test getmedianpivot([8,2,4,5,7,1], 1, 6) == 3  
@test getmedianpivot([4,5,6,7], 1, 4) == 2
@test getmedianpivot([1,2,3,4,5,6,7], 3, 4) == 3

# Run code and test
firstpivottotal = qs!(a, 1, length(a), getfirstpivot)
@test a == sanity
@test length(a) == length(sanity)

lastpivottotal = qs!(b, 1, length(b), getlastpivot)
@test b == sanity
@test length(b) == length(sanity)

medianpivottotal = qs!(c, 1, length(c), getmedianpivot)
@test c == sanity
@test length(c) == length(sanity)

println("Comparisons using first element as pivot: $firstpivottotal")
println("Comparisons using last element as pivot: $lastpivottotal")
println("Comparisons using median element as pivot: $medianpivottotal")

@time qs!(d, 1, length(d), getmedianpivot)
