mogrify -format png *.bmp
for i in *.png
do
	convert "$i" -transparent '#5e4229' "$i"
done
