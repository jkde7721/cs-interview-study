def bubbleSort(arr):
    
    arrLength = len(arr)
    
    #! ( 배열크기 - 1 )번 만큼 수행
    for i in range(arrLength-1) :
        #! 한 텀마다 swap 수행. 이전 텀에서 가장 큰 수는 맨 뒤로 이동했으므로, (arrLength - 1 - i) 만큼 수행
        for j in range(arrLength-1-i):
            if (arr[j] > arr[j+1]):
                arr[j], arr[j+1] = arr[j+1], arr[j]
        
    return arr
print(bubbleSort([8,4,6,2,9,1,3,7,5]))


def selectionSort(arr):
    
    arrLength = len(arr)
    
    for i in range(arrLength - 1):
        minIndex = i
        
        for j in range(i+1, arrLength):
            if arr[minIndex] > arr[j]:
                minIndex = j
                
        arr[minIndex], arr[i] = arr[i], arr[minIndex]
    
    return arr
print(selectionSort([8,4,6,2,9,1,3,7,5]))


def insertionSort(arr):
    
    arrLength = len(arr)
    
    for i in range(1, arrLength):
        key, j = arr[i], i-1
        
        while j >= 0 and key < arr[j]:
            arr[j+1] = arr[j]
            j -= 1
        
        arr[j+1] = key
    return arr

print(insertionSort([8,4,6,2,9,1,3,7,5]))


def mergeSort(arr):
    if len(arr) <= 1 :
        return arr
    
    mid = len(arr) // 2
    left, right = mergeSort(arr[:mid]), mergeSort(arr[mid:])
    
    #! merge 1 - 각 배열 비교하면서 수 넣기 
    i, j, k = 0, 0, 0
    while i < len(left) and j < len(right):
        if left[i] < right[j]:
            arr[k] = left[i]
            i += 1
        else:
            arr[k] = right[j]
            j += 1
        k += 1
        
    #! merge 2 - 남은 배열의 원소 합치기
    while i < len(left):
        arr[k] = left[i]
        i += 1
        k += 1
    while j < len(right):
        arr[k] = right[j]
        j += 1
        k += 1
    
    return arr
    
print(mergeSort([8,4,6,2,9,1,3,7,5]))


def quickSort(arr, start, end):
    # 원소 1개 아닐 경우 퀵소트 실행
    if start < end:    # arr을 그대로 받기 때문에, if len(arr) >= 1: 불가능
        pivot = split(arr, start, end)
        
        # pivot 제외 퀵소트 실행
        quickSort(arr, start, pivot-1)
        quickSort(arr, pivot+1, end)
        
    return arr
    
def split(arr, start, end):
    pivot, left, wall = end, start, start
    
    # pivot 기준으로 왼쪽 -> 작은 수, 오른쪽 -> 큰 수
    while left < pivot:
        if arr[left] < arr[pivot]:
            arr[wall], arr[left] = arr[left], arr[wall]
            wall += 1
        
        left += 1
    
    # pivot과 경계 바꾸기 
    arr[wall], arr[pivot] = arr[pivot], arr[wall]
    
    return wall
    
print(quickSort([8,4,6,2,9,1,3,7,5], 0, 8))       

def heapSort(arr):
    arrLength = len(arr)
    
    # heap 형태로 정렬
    for i in range(arrLength, -1, -1):
        heapify(arr, arrLength, i)
    
    #  root와 마지막값을 바꿔 정렬하고 바뀐값을 기준으로 다시 heapify
    for i in range(n-1,0,-1):
        arr[i],arr[0] = arr[0],arr[i]
        heapify(arr,i,0)
        
    return arr
        
        
def heapify(arr, n, i):
    root = i
    left = 2 * i + 1
    right = 2 * i + 2
    
    # 왼쪽 노드가 존재하고, 루트보다 더 큰 값일 때
    if left < n and arr[left] > arr[root]:
        root = left
    
    # 오른쪽 노드가 존재하고, 루트보다 더 큰 값일 때
    if right < n and arr[right] > arr[root]:
        root = right
    
    # 왼쪽, 오른쪽 자식 노드들과 바꿔줄 위치를 찾았을 때
    if root != i :
        arr[i], arr[root] = arr[root], arr[i]
        heapify(arr, n, root)
        
