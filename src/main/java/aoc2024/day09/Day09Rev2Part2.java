package aoc2024.day09;

import aoc2024.common.ListUtils;
import aoc2024.common.Pair;
import aoc2024.common.ReadInput;
import aoc2024.common.TypeUtils;

import java.io.IOException;
import java.util.*;

public class Day09Rev2Part2 {

    public static void main(String[] args) throws IOException {

        List<String> lines = ReadInput.readInputToList("inputs/day09.txt");
        System.out.println(solve(lines.get(0)));  //6323761685944

    }

    protected static long solve(String line) {
        List<Block> blocks = parseBlocks(line);
        List<Block> compressed = blocks; // compressBlocks(blocks);
        return checksum(compressed);
    }

    private static List<Integer> parseBlockSizes(String line) {
        List<Integer> blockSizes = new ArrayList<>(line.length());
        for (int i = 0; i < line.length(); i++) {
            blockSizes.add(Integer.parseInt(String.valueOf(line.charAt(i))));
        }
        return blockSizes;
    }

    private static List<Block> parseBlocks(String line) {
        List<Integer> blockSizes = parseBlockSizes(line);
        ParserState initial = new ParserState();
        return ListUtils.foldLeft(
                blockSizes,
                initial,
                (state, blockSize) -> {
                    int currentFileId = state.getNextFileId();
                    Block newBlock = state.isNextIsFile()
                            ? new File(blockSize, currentFileId)
                            : new Empty(blockSize);

                    List<Block> blocksCollected = state.getBlocks();
                    blocksCollected.add(newBlock);

                    return new ParserState(
                            !state.isNextIsFile(),
                            state.isNextIsFile() ? currentFileId + 1 : currentFileId,
                            blocksCollected
                    );
                }
        ).getBlocks();
    }

    private static long checksum(List<Block> blocks) {
        List<Block> oneLengthBlocks = blocks.stream().flatMap((block) ->
                // Note: size is not being changed, actually, but not used anymore...
                ListUtils.repeat(block, block.getSize()).stream()
        ).toList();

        return ListUtils.zipWithIndex(oneLengthBlocks).stream()
                .mapToLong(blockWithIndex -> { // (block, index) ->
                    Block block = blockWithIndex.getFirst();
                    int index = blockWithIndex.getSecond();

                    Optional<File> optFile = TypeUtils.safeCast(block, File.class);
                    long value = optFile.map(File::getId).orElseGet(() -> 0);
                    return value * index;
                })
                .sum();
    }
/*
    private static List<Pair<NumWSize, Integer>> createDiskMapPart3(List<Pair<Integer, Integer>> input) {
        List<NumWSize> numbers = new ArrayList<>();
        int i = 0;
        for (Pair<Integer, Integer> pair : input) {
            if (pair.getSecond() % 2 == 0) {
                if (pair.getFirst() != 0) {
                    numbers.add(new NumWSize(Optional.of(i), pair.getFirst()));
                }
                i++;
            } else {
                if (pair.getFirst() != 0) {
                    numbers.add(new NumWSize(Optional.empty(), pair.getFirst()));
                }
            }
        }
        return ListUtils.zipWithIndex(numbers);
    }


    private static List<Pair<NumWSize, Integer>> orderNumbersPart3(List<Pair<NumWSize, Integer>> input) {
        List<Pair<NumWSize, Integer>> reverseOrderNumbers =
                new ArrayList<>(input.stream()
                        .filter(pair -> pair.getFirst().getNumber().isPresent())
                        .toList());
        reverseOrderNumbers.sort((a, b) -> Integer.compare(a.getFirst().getNumber().get(), b.getFirst().getNumber().get()));
        Collections.reverse(reverseOrderNumbers);

        List<Pair<NumWSize, Integer>> result = new ArrayList<>(input);
        for (Pair<NumWSize, Integer> pair : reverseOrderNumbers) {
            result = relocateOneNumberPart3(result, pair);
        }

        return result;
    }


    private static List<Pair<NumWSize, Integer>> relocateOneNumberPart3(List<Pair<NumWSize, Integer>> input, Pair<NumWSize, Integer> numberToExamine) {
        System.out.println(numberToExamine.getFirst().getNumber().get());
        Optional<Pair<NumWSize, Integer>> emptyStartPoint = input.stream()
                .filter(pair -> pair.getFirst().getNumber().isEmpty())
                .filter(pair -> pair.getFirst().getSize() >= numberToExamine.getFirst().getSize())
                .filter(pair -> pair.getSecond() < numberToExamine.getSecond())
                .findFirst();
        List<NumWSize> result = new ArrayList<>();

        boolean hasNewEmptyPlace = false;
        if (emptyStartPoint.isPresent()) {
            int newSpaceLength = emptyStartPoint.get().getFirst().getSize() - numberToExamine.getFirst().getSize();
            for (Pair<NumWSize, Integer> pair : input) {
                if (hasNewEmptyPlace) {
                    if (pair.getFirst().getNumber().isEmpty()) {
                        result.add(new NumWSize(Optional.empty(), pair.getFirst().getSize() + newSpaceLength));
                        hasNewEmptyPlace = false;
                    } else if (pair.getFirst().equals(numberToExamine.getFirst())) {
                        result.add(new NumWSize(Optional.empty(), newSpaceLength + numberToExamine.getFirst().getSize()));
                        hasNewEmptyPlace = false;
                    } else {
                        result.add(new NumWSize(Optional.empty(), newSpaceLength));
                        result.add(pair.getFirst());
                        hasNewEmptyPlace = false;
                    }
                } else if (pair.equals(emptyStartPoint.get())) {
                    result.add(numberToExamine.getFirst());
                    hasNewEmptyPlace = newSpaceLength > 0;
                } else if (pair.getFirst().equals(numberToExamine.getFirst())) {
                    result.add(new NumWSize(Optional.empty(), numberToExamine.getFirst().getSize()));
                } else {
                    result.add(pair.getFirst());
                }
            }
            return ListUtils.zipWithIndex(result);

        } else {
            return input;
        }
    }


    private static List<Pair<Optional<Integer>, Integer>> createListForPart3(List<Pair<NumWSize, Integer>> input) {
        List<Optional<Integer>> numbers = new ArrayList<>();
        for (Pair<NumWSize, Integer> pair : input) {
            for (int j = 0; j < pair.getFirst().getSize(); j++) {
                numbers.add(pair.getFirst().getNumber());
            }
        }
        return ListUtils.zipWithIndex(numbers);
    }
*/
}

// Scala: case class ParserState(nextIsFile: Boolean, nextFileId: Int, blocks: List[Blocks])
class ParserState {
    private boolean nextIsFile = true;

    private int nextFileId = 0;

    private List<Block> blocks = new ArrayList<>();

    public ParserState() {}

    public ParserState(boolean nextIsFile, int nextFileId, List<Block> blocks) {
        this.nextIsFile = nextIsFile;
        this.nextFileId = nextFileId;
        this.blocks = blocks;
    }

    public boolean isNextIsFile() {
        return nextIsFile;
    }

    public int getNextFileId() {
        return nextFileId;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParserState that = (ParserState) o;
        return nextIsFile == that.nextIsFile && nextFileId == that.nextFileId && Objects.equals(blocks, that.blocks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextIsFile, nextFileId, blocks);
    }
}

abstract class Block {
    private final int size;

    public Block(int size) {
        this.size = size;
    }

    int getSize() {
        return size;
    }
}

class Empty extends Block {

    public Empty(int size) {
        super(size);
    }
}

class File extends Block {
    private final int id;

    public File(int size, int id) {
        super(size);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
